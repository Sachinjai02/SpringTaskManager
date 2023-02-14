package com.study.springtaskmanager.repositories;

import com.study.springtaskmanager.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
