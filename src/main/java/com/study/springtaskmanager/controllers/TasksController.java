package com.study.springtaskmanager.controllers;

import com.study.springtaskmanager.services.NotesService;
import com.study.springtaskmanager.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;
    final NotesService notesService;

    @Autowired
    public TasksController(TasksService tasksService, NotesService notesService) {
        this.tasksService = tasksService;
        this.notesService = notesService;
    }



}
