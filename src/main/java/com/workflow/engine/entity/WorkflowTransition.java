package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WorkflowTransition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private WorkflowState fromState;
    
    @ManyToOne
    private WorkflowState toState;
    
    private String condition;  // Optional: Conditions for this transition
}
