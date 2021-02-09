package com.example.notes.service;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.NoteDto;
import java.util.List;

/**
 * @author miran_jayasinghe
 */
public interface NoteService {

    ApiResponse createNote(NoteDto noteDto, String username);

    List<String> getArchiveNotes(String username);

    ApiResponse getNotes(String username);

    ApiResponse deleteNote(String username, Long noteId);

    ApiResponse updateNoteArchiveStatus(Long id, NoteDto noteDto,String username);

    ApiResponse updateNote(Long id, NoteDto noteDto, String username);

}
