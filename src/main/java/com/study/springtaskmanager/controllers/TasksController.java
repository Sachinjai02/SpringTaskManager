package com.study.springtaskmanager.controllers;

import com.study.springtaskmanager.dtos.TaskDto;
import com.study.springtaskmanager.dtos.utils.TaskDtoUtils;
import com.study.springtaskmanager.entities.TaskEntity;
import com.study.springtaskmanager.exception.TaskNotFoundException;
import com.study.springtaskmanager.services.NotesService;
import com.study.springtaskmanager.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasksFiltered(@RequestParam Map<String, String> requestParams) {
        List<TaskEntity> allTasksFiltered = tasksService.getAllTasksFiltered(requestParams);
        return new ResponseEntity<>(TaskDtoUtils.convertTaskEntityToDto(allTasksFiltered), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") Integer taskId) {
        TaskEntity task = tasksService.getTaskById(taskId);
        return new ResponseEntity<>(TaskDtoUtils.convertTaskEntityToDto(task), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createTask(@RequestBody TaskDto inputTask, HttpServletRequest request) {
        TaskEntity createdTask = tasksService.createTask(TaskDtoUtils.convertTaskDtoToTaskEntity(inputTask));
        String location = ServletUriComponentsBuilder.fromContextPath(request).
                build().toUriString() + "/" + createdTask.getId();
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> patchTask(@RequestBody TaskDto patchDetails, @PathVariable(name = "id") Integer taskId) {
        TaskEntity patchedTask = tasksService.patchTask(taskId, TaskDtoUtils.convertTaskDtoToTaskEntity(patchDetails));
        return ResponseEntity.accepted().body(TaskDtoUtils.convertTaskEntityToDto(patchedTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto updateDetails, @PathVariable(name = "id") Integer taskId) {
        TaskEntity updatedTask = tasksService.updateTask(taskId, TaskDtoUtils.convertTaskDtoToTaskEntity(updateDetails));
        return ResponseEntity.accepted().body(TaskDtoUtils.convertTaskEntityToDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Integer taskId) {
        tasksService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return new ResponseEntity<>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
