package com.study.springtaskmanager.services;

import com.study.springtaskmanager.dtos.TaskDto;
import com.study.springtaskmanager.dtos.utils.TaskDtoUtils;
import com.study.springtaskmanager.entities.TaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan({ "com.study.springtaskmanager"})
public class TaskServiceTests {

    @Autowired
    private TasksService tasksService;

    @Test
    public void testCreateTask() {
        TaskEntity createdTask = createTask("task1", "desc1", true, new Date());
        assertNotNull(createdTask);
        //FIXME: assertNotNull(createdTask.getCreatedAt());
        assertNotNull(createdTask.getId());
    }

    @Test
    public void testGetAllTasks() {
        Map<String, String> noFilter = new HashMap<>();
        createTask("task1", "desc1", true, new Date());
        createTask("task2", "desc2", true, new Date());
        List<TaskEntity> allTasksUnFiltered = tasksService.getAllTasksFiltered(noFilter);
        assertEquals(2, allTasksUnFiltered.size());
    }

    @Test
    public void testGetAllTasksFilteredByTitle() {
        Map<String, String> titleFilter = new HashMap<>();
        titleFilter.put("title", "task1");
        createTask("task1", "desc1", true, new Date());
        createTask("task2", "desc2", true, new Date());
        List<TaskEntity> allTasksFiltered = tasksService.getAllTasksFiltered(titleFilter);
        assertEquals(1, allTasksFiltered.size());
    }

    @Test
    public void testGetAllTasksFilteredByCompleted() {
        Map<String, String> completionFilter = new HashMap<>();
        completionFilter.put("completed", "false");
        createTask("task1", "desc1", true, new Date());
        createTask("task2", "desc2", true, new Date());
        List<TaskEntity> allTasksFiltered = tasksService.getAllTasksFiltered(completionFilter);
        assertEquals(0, allTasksFiltered.size());
    }

    private TaskEntity createTask(String title, String desc, boolean completed, Date date) {
        return this.tasksService
                .createTask(TaskDtoUtils.
                        convertTaskDtoToTaskEntity(createTaskDto(
                                title, desc, completed, date)));
    }
    private TaskDto createTaskDto(String title, String desc, boolean completed, Date date) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title);
        taskDto.setDescription(desc);
        taskDto.setCompleted(completed);
        taskDto.setDueDate(date);
        return taskDto;
    }
}
