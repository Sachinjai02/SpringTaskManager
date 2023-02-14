package com.study.springtaskmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Setter
@Getter
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 150)
    String title;
    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name = "due_date", nullable = true)
    Date dueDate;

    @OneToMany(mappedBy = "task")
    List<NoteEntity> notes;
}
