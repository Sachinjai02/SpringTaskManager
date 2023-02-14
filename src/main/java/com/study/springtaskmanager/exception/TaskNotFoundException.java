package com.study.springtaskmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Integer taskId) {
        super("Task with " + taskId + " could not be found");
    }
}
