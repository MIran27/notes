package com.example.notes.service;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.NoteDto;
import com.example.notes.entity.Note;
import com.example.notes.entity.NoteUser;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.impl.NoteServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author miran_jayasinghe
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteServiceImpl noteService;
    @Mock
    private NoteRepository noteRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateNote() throws Exception{

        NoteDto inputNoteDto = new NoteDto();
        inputNoteDto.setNote("test_note");
        inputNoteDto.setTitle("test_title");
        inputNoteDto.setArchive(true);
        String username =  "123";
        NoteUser noteUser = new NoteUser();
        noteUser.setUsername("test_user");
        noteUser.setPassword("test_password");
        Note returned = new Note();
        NoteUser returnNoteUser = new NoteUser();
        returnNoteUser.setUsername("123");
        returnNoteUser.setPassword("123");
        returned.setNote("test_note");
        returned.setTitle("test_title");
        returned.setArchive(true);
        returned.setUser(returnNoteUser);
        Mockito.when(userRepository.findById(username)).thenReturn(Optional.of(noteUser));
        Mockito.when(noteRepository.save(any())).thenReturn(returned);
        ApiResponse result = noteService.createNote(inputNoteDto, username);
        Assert.assertEquals("test_title", ((NoteDto) result.getResult()).getTitle());
    }

    @Test
    public void testDeleteNote(){
        Note input = new Note();
        Mockito.when(noteRepository.getOne(1L)).thenReturn(input);
        doNothing().when(noteRepository).delete(input);
        noteService.deleteNote("test_note", (long) 1);
    }

    @Test
    public void testUpdateNote(){
        Note input = new Note();
        input.setTitle("test_title");
        input.setNote("test_note");
        Note returned = new Note();
        returned.setTitle("test_title");
        returned.setNote("update_test_note");
        NoteDto inputNoteDto = new NoteDto();
        inputNoteDto.setNote("test_note");
        inputNoteDto.setTitle("test_title");
        inputNoteDto.setArchive(true);
        Mockito.when(noteRepository.updateNote(true,inputNoteDto.getNoteId(),inputNoteDto.getUsername(),inputNoteDto.getNote(),inputNoteDto.getTitle())).thenReturn(1);
        Mockito.when(noteRepository.save(input)).thenReturn(returned);
        ApiResponse result = noteService.updateNote((long)1, inputNoteDto,"note_user");

        Assert.assertEquals(204, result.getStatus());
    }

    @Test
    public void testGetArchiveNotes(){
        Note note = new Note();
        note.setArchive(true);
        String username =  "123";
        note.setTitle("test_title");
        NoteUser noteUser = new NoteUser();
        noteUser.setUsername("test_user1");
        noteUser.setPassword("test_password1");
        List <Note> notes = new ArrayList<>();
        Mockito.when(userRepository.findById(username)).thenReturn(Optional.of(noteUser));
        Mockito.when(noteRepository.findAllByUserAndArchive(noteUser,true)).thenReturn(notes);
        List<String> apiResponse = noteService.getArchiveNotes("test_user");

    }
}
