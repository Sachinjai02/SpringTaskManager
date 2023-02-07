package com.study.taskmanager;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TaskController {
    private final List<Task> taskList;
    private AtomicInteger taskId = new AtomicInteger(0);

    public TaskController() {
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.incrementAndGet(), "Task 1", "Description 1", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 2", "Description 2", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 3", "Description 3", new Date()));
    }


    /**
     * Show all existing tasks
     * GET /tasks
     * @return List of tasks
     */
    @GetMapping("/tasks")
    List<Task> getTasks() {
        return taskList;
    }

    /**
     * Create a new task
     * POST /tasks
     * Body:
     *  <pre>
     *      {
     *          "title": "Task 4",
     *          "description": "Description 4",
     *          "dueDate": "2021-01-01"
     *      }
     *  </pre>
     * @param task Task object sent by client
     * @return Task object created
     */
    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task) {
        var newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Get a task by id
     * @param id
     * @return Task object
     */
    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable("id") Integer id) {
        return getTaskById(id);
    }

    /**
     * Delete a task by given id
     * @param id Task id to delete
     * @return the deleted task
     */
    @DeleteMapping("/tasks/{id}")
    Task deleteTask(@PathVariable("id") Integer id) {
        var inputTask = getTaskById(id);
        this.taskList.remove(inputTask);
        return inputTask;
    }

    /**
     * Update a task by given id
     * @param id Task id to update
     * @param task Task object sent by client
     * @return the updated task
     */
    @PatchMapping("/tasks/{id}")
    Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        var inputTask = getTaskById(id);
        if(task.getDescription() != null) {
            inputTask.setDescription(task.getDescription());
        }
        if(task.getTitle() != null) {
            inputTask.setTitle(task.getTitle());
        }
        if(task.getDueDate() != null) {
            inputTask.setDueDate(task.getDueDate());
        }
        return inputTask;
    }

    private Task getTaskById(Integer taskId) {
        var taskOptional = taskList.stream().filter(task -> task.getId().equals(taskId)).findAny();
        if(taskOptional.isEmpty()) {
            throw new TaskNotFoundException(taskId);
        }
        return taskOptional.get();
    }
}