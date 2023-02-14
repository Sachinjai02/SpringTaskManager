package com.study.springtaskmanager.dtos.utils;

import com.study.springtaskmanager.dtos.TaskDto;
import com.study.springtaskmanager.entities.TaskEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDtoUtils {

    public static TaskEntity convertTaskDtoToTaskEntity(TaskDto taskDto) {
        return convertTaskDtoToTaskEntity(Collections.singletonList(taskDto)).get(0);
    }
    public static TaskDto convertTaskEntityToDto(TaskEntity task) {
        return convertTaskEntityToDto(Collections.singletonList(task)).get(0);
    }

    public static List<TaskDto> convertTaskEntityToDto(List<TaskEntity> tasks) {
        return tasks.stream().map(task -> {
            //TODO: simplify this by objectMappers
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitle(task.getTitle());
            taskDto.setDescription(task.getDescription());
            taskDto.setCompleted(task.getCompleted());
            taskDto.setDueDate(task.getDueDate());
            return taskDto;
        }).collect(Collectors.toList());
    }

    public static List<TaskEntity> convertTaskDtoToTaskEntity(List<TaskDto> tasksDto) {
        return tasksDto.stream().map(task -> {
            //TODO: simplify this by objectMappers
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setTitle(task.getTitle());
            taskEntity.setDescription(task.getDescription());
            taskEntity.setCompleted(task.getCompleted());
            taskEntity.setDueDate(task.getDueDate());
            return taskEntity;
        }).collect(Collectors.toList());
    }
}
