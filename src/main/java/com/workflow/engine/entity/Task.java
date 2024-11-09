package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime lastUpdatedDate;
    
    @ManyToOne
    private WorkflowState currentState;
    
    @ManyToOne
    private User assignee;
}
