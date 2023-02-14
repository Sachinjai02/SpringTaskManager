package com.study.springtaskmanager.services;

import com.study.springtaskmanager.repositories.NotesRepository;
import com.study.springtaskmanager.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksService {
    final TasksRepository tasksRepository;
    final NotesRepository notesRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }
}
