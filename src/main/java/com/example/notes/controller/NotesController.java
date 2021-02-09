package com.example.notes.controller;

import java.util.List;
import com.example.notes.common.ApiResponse;
import com.example.notes.common.NoteDto;
import com.example.notes.service.NoteService;
import com.example.notes.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author miran_jayasinghe
 */
@RestController
@RequestMapping("/api/v1")
public class NotesController{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private NoteService noteService;



    @PostMapping("/note")
    public ApiResponse createNote(@RequestBody NoteDto noteDto, HttpServletRequest httpServletRequest) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = getUserName(authorizationHeader);
        ApiResponse apiResponse = new ApiResponse();
        try {
            return noteService.createNote(noteDto, username);
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }

    }

    @PutMapping("/note/{id}")
    public ApiResponse updateNote(@RequestBody NoteDto noteDto, HttpServletRequest httpServletRequest, @PathVariable Long id) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        try {
            String username = getUserName(authorizationHeader);
            return noteService.updateNote(id, noteDto, username);
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }
    }

    @DeleteMapping("/note/{id}")
    public ApiResponse deleteNote(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        try {
            String userName = getUserName(authorizationHeader);
            return noteService.deleteNote(userName, id);
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }
    }

    @PutMapping("/note/archive/{id}")
    public ApiResponse updateNoteArchiveStatus(@RequestBody NoteDto noteDto,HttpServletRequest httpServletRequest, @PathVariable Long id) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        try {
            String username = getUserName(authorizationHeader);
            return noteService.updateNoteArchiveStatus(id, noteDto, username);
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }
    }

    @GetMapping("/note")
    public ApiResponse getNote(HttpServletRequest httpServletRequest) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        try {
            String username = getUserName(authorizationHeader);
            return noteService.getNotes(username);
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }

    }

    @GetMapping("/note/archive")
    public ApiResponse getArchive(HttpServletRequest httpServletRequest) {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = getUserName(authorizationHeader);
        try {
            List<String> strings = noteService.getArchiveNotes(username);
            ApiResponse apiResponse = new ApiResponse(200, "SUCCESS", strings);
            apiResponse.setResult(strings);
            return apiResponse;
        }catch (Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }
    }

    private String getUserName(String authorizationHeader){
        String jwt = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        return username;
    }
}
