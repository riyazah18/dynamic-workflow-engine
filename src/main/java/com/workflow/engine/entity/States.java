package com.workflow.engine.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class States {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isInitial;
    private boolean isFinal;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;
}
