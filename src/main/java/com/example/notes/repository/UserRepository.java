package com.example.notes.repository;

import com.example.notes.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

/**
 * @author miran_jayasinghe
 */
public interface UserRepository extends JpaRepository<NoteUser, String> {
    @Query("select u from NoteUser u where u.username = ?1")
    NoteUser findByUsername(String username);

    Optional<NoteUser> findById(String id);
}
