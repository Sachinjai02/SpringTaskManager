package com.study.springtaskmanager.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;
    private Integer id;
}
