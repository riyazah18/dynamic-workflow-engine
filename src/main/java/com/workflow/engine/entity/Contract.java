package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String data;
}

