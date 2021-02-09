package com.example.notes.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * @author miran_jayasinghe
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteDto implements Serializable {

    private Long noteId;

    private String archive_status;

    private String note;

    private String title;

    private String username;

    private String userId;

    private Boolean archive;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArchive_status() {
        return archive_status;
    }

    public void setArchive_status(String archive_status) {
        this.archive_status = archive_status;
    }
}
