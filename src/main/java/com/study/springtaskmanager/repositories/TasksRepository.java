package com.study.springtaskmanager.repositories;

import com.study.springtaskmanager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);

    // Ideally this is 'business logic' terminology (i.e. 'overdue') so shouldn't be here
    @Query("SELECT t FROM tasks t WHERE t.completed = false AND t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);

    List<TaskEntity> findAllByTitleAndAndCompleted(String title, boolean completed);
    List<TaskEntity> findAllByTitle(String title);
}
