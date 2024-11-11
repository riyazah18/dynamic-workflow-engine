package com.workflow.engine.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestModel {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private Long currentStateId;    // Assuming we pass a state ID for simplicity
    private Long assigneeId;         // ID of the user to whom the task is assigned
}
