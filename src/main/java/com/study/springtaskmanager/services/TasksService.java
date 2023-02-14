package com.study.springtaskmanager.services;

import com.study.springtaskmanager.constants.TaskManagerUriConstants;
import com.study.springtaskmanager.entities.TaskEntity;
import com.study.springtaskmanager.exception.TaskNotFoundException;
import com.study.springtaskmanager.repositories.NotesRepository;
import com.study.springtaskmanager.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TasksService {
    final TasksRepository tasksRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<TaskEntity> getAllTasksFiltered(Map<String, String> filterParams) {
        if(filterParams.containsKey(TaskManagerUriConstants.TASK_REQUEST_ATTR_COMPLETED) && filterParams.containsKey(TaskManagerUriConstants.TASK_REQUEST_ATTR_TITLE)) {
            return this.tasksRepository.findAllByTitleAndAndCompleted(
                    filterParams.get(TaskManagerUriConstants.TASK_REQUEST_ATTR_TITLE),
                    Boolean.valueOf(filterParams.get(TaskManagerUriConstants.TASK_REQUEST_ATTR_COMPLETED)));
        } else if(filterParams.containsKey(TaskManagerUriConstants.TASK_REQUEST_ATTR_TITLE)) {
            return this.tasksRepository.findAllByTitle(
                    filterParams.get(TaskManagerUriConstants.TASK_REQUEST_ATTR_TITLE));
        } else if(filterParams.containsKey(TaskManagerUriConstants.TASK_REQUEST_ATTR_COMPLETED)) {
            return this.tasksRepository.findAllByCompleted(
                    Boolean.valueOf(filterParams.get(TaskManagerUriConstants.TASK_REQUEST_ATTR_COMPLETED)));
        } else {
            return this.tasksRepository.findAll();
        }
    }

    public TaskEntity getTaskById(Integer taskId) {
        return getTaskFromDB(taskId);
    }


    public TaskEntity createTask(TaskEntity task) {
        return this.tasksRepository.save(task);
    }

    public TaskEntity patchTask(Integer taskId, TaskEntity patchDetails) {
        TaskEntity task = getTaskFromDB(taskId);
        if(patchDetails.getCompleted() != null) {
            task.setCompleted(patchDetails.getCompleted());
        }
        if(patchDetails.getDescription() != null) {
            task.setDescription(patchDetails.getDescription());
        }
        if(patchDetails.getTitle() != null) {
            task.setTitle(patchDetails.getTitle());
        }
        if(patchDetails.getDueDate() != null) {
            task.setDueDate(patchDetails.getDueDate());
        }
        return this.tasksRepository.save(task);
    }

    public TaskEntity updateTask(Integer taskId, TaskEntity taskDetailsToUpdate) {
        TaskEntity task = getTaskFromDB(taskId);
        taskDetailsToUpdate.setId(task.getId());
        return this.tasksRepository.save(taskDetailsToUpdate);
    }


    public void deleteTask(Integer taskId) {
        TaskEntity task = getTaskFromDB(taskId);
        this.tasksRepository.delete(task);
    }

    private TaskEntity getTaskFromDB(Integer taskId) {
        Optional<TaskEntity> taskOptional = this.tasksRepository.findById(taskId);
        if(! taskOptional.isPresent()) {
            throw new TaskNotFoundException(taskId);
        }
        return taskOptional.get();
    }


}
