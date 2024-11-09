package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class WorkflowState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne
    private Workflow workflow;
    
    @OneToMany(mappedBy = "fromState")
    private List<WorkflowTransition> transitions;
}
