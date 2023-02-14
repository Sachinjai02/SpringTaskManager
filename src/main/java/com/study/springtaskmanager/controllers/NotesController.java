package com.study.springtaskmanager.controllers;

import com.study.springtaskmanager.dtos.NoteDto;
import com.study.springtaskmanager.dtos.TaskDto;
import com.study.springtaskmanager.dtos.utils.NoteDtoUtils;
import com.study.springtaskmanager.dtos.utils.TaskDtoUtils;
import com.study.springtaskmanager.entities.NoteEntity;
import com.study.springtaskmanager.entities.TaskEntity;
import com.study.springtaskmanager.services.NotesService;
import com.study.springtaskmanager.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
public class NotesController {

    final NotesService notesService;
    final TasksService tasksService;

    @Autowired
    public NotesController(NotesService notesService,  TasksService tasksService) {
        this.notesService = notesService;
        this.tasksService = tasksService;
    }

    @GetMapping("/tasks/{id}/notes")
    public ResponseEntity<List<NoteDto>> getNotesForATask(@PathVariable(name = "id") Integer taskId) {
        TaskEntity task = tasksService.getTaskById(taskId);
        return new ResponseEntity<>(NoteDtoUtils.convertNoteEntityToDto(task.getNotes()), HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}/notes")
    public ResponseEntity<Void> createNote(@PathVariable(name = "id") Integer taskId,
                                           @RequestBody NoteDto inputNote) {
        notesService.createNote(taskId,
                NoteDtoUtils.convertNoteDtoToNoteEntity(inputNote));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}/notes/{noteId}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Integer taskId,
                                           @PathVariable(name = "noteId") Integer noteId) {
        notesService.deleteNote(taskId, noteId);
        return ResponseEntity.noContent().build();
    }
}
