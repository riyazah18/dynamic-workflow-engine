package com.workflow.engine.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "workflow")
    private List<WorkflowState> states;
}
