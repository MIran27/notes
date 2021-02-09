package com.example.notes.service.impl;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.NoteDto;
import com.example.notes.entity.Note;
import com.example.notes.entity.NoteUser;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miran_jayasinghe
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository  userRepository;

    @Override
    @Transactional
    public ApiResponse deleteNote(String username, Long noteID){
        NoteUser noteUser = getNoteUser(username);
        noteRepository.deleteByUserAndId(noteUser, noteID);
        return new ApiResponse(200,"SUCCESS","Successfully deleted");
    }

    @Override
    public ApiResponse createNote(NoteDto noteDto, String username) {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setArchive(noteDto.getArchive());
        note.setNote(noteDto.getNote());
        NoteUser noteUser = getNoteUser(username);
        note.setUser(noteUser);
        Note returnNote = noteRepository.save(note);
        noteDto = createNoteResponse(returnNote);
        return new ApiResponse(201,"SUCCESSFULLY CREATE A NOTE",noteDto);
    }

    @Override
    public List<String> getArchiveNotes(String username){
        NoteUser noteUser = getNoteUser(username);
        return noteRepository.findAllByUserAndArchive(noteUser,true).stream().map(o -> o.getTitle()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApiResponse updateNoteArchiveStatus(Long id, NoteDto noteDto, String username){
        int note  = noteRepository.updateArchiveStatus(noteDto.getArchive(), id,username);
        if (note == 1) {
            return new ApiResponse(200, "SUCCESSFULLY UPDATED", null);
        }
        else{
            return new ApiResponse(204, "NO CONTENT", null);
        }
    }

    @Override
    @Transactional
    public ApiResponse updateNote(Long id, NoteDto noteDto, String username){
        int note  = noteRepository.updateNote(noteDto.getArchive(), id, username, noteDto.getNote(), noteDto.getTitle());
        if (note == 1) {
            return new ApiResponse(200, "SUCCESSFULLY UPDATED", null);
        }
        else{
            return new ApiResponse(204, "NO CONTENT", null);
        }
    }

    @Override
    public ApiResponse getNotes(String username){
        NoteUser noteUser = getNoteUser(username);
        List<Note> notes =  noteRepository.findAllByUserAndArchive(noteUser,false);
        List<NoteDto> noteDtos = new ArrayList<>();
        for (Note note: notes) {
            NoteDto noteDto = createNoteResponse(note);
            noteDtos.add(noteDto);
        }
        return new ApiResponse(200,"SUCCESS",noteDtos);
    }

    private NoteDto createNoteResponse(Note note){
        NoteDto noteDto = new NoteDto();
        noteDto.setTitle(note.getTitle());
        noteDto.setNoteId(note.getId());
        if (note.getArchive() == true) {
            noteDto.setNote(null);
            noteDto.setArchive_status("Archived Note");
        }else{
            noteDto.setNote(note.getNote());
        }
        return noteDto;
    }

    private NoteUser getNoteUser(String username){
        return userRepository.findById(username).orElse(null);
    }
}
