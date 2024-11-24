package com.workflow.engine.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestModel {
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private String createdBy;
    private Long initialState =1L;
}
