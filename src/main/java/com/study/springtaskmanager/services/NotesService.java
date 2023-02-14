package com.study.springtaskmanager.services;

import com.study.springtaskmanager.entities.NoteEntity;
import com.study.springtaskmanager.entities.TaskEntity;
import com.study.springtaskmanager.repositories.NotesRepository;
import com.study.springtaskmanager.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesService {

    private final NotesRepository notesRepository;

    private final TasksService tasksService;
    @Autowired
    public NotesService(NotesRepository notesRepository,
                        TasksService tasksService) {
        this.notesRepository = notesRepository;
        this.tasksService = tasksService;
    }

    public NoteEntity createNote(Integer taskId, NoteEntity noteEntity) {
        TaskEntity taskEntity = this.tasksService.getTaskById(taskId);
        noteEntity.setTask(taskEntity);
        NoteEntity createdNote = this.notesRepository.save(noteEntity);
        return createdNote;
    }

    public void deleteNote(Integer taskId, Integer noteId) {
        TaskEntity taskEntity = this.tasksService.getTaskById(taskId);
        Optional<NoteEntity> noteEntityOptional = taskEntity.getNotes().stream().
                filter(note -> note.getId().equals(noteId)).findAny();
        if(noteEntityOptional.isPresent()) {
            this.notesRepository.delete(noteEntityOptional.get());
        }
    }
}
