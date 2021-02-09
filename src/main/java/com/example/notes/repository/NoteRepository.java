package com.example.notes.repository;

import com.example.notes.entity.Note;
import com.example.notes.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author miran_jayasinghe
 */
@Repository
@EnableJpaRepositories
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Note n SET n.archive = :archive WHERE n.id = :noteId and n.user.username = :username")
    Integer updateArchiveStatus(@Param("archive") boolean archive, @Param("noteId") Long noteId, @Param("username") String username);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Note n SET n.archive = :archive, n.note = :note, n.title = :title  WHERE n.id = :noteId and n.user.username = :username")
    Integer updateNote(@Param("archive") boolean archive, @Param("noteId") Long noteId, @Param("username") String username, @Param("note") String note, @Param("title") String title);

    @Query("select u from Note u where u.user.username = ?1")

    Note findByUsername(String username);

    List<Note> findAllByUser(NoteUser noteUser);

    List<Note> findAllByUserAndArchive(NoteUser noteUser, Boolean archive);

    void deleteByUserAndId(  NoteUser noteUser,Long id );
}
