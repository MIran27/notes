package com.example.notes.entity;

import javax.persistence.*;


@Entity
@Table(name = "NOTE")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private String note;

    @Column(name = "archive")
    private Boolean archive;

    @OneToOne(targetEntity = NoteUser.class)
    private NoteUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public NoteUser getUser() {
        return user;
    }

    public void setUser(NoteUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
