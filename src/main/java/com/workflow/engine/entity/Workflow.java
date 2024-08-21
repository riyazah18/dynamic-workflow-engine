package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}