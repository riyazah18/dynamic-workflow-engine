package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event;

    @ManyToOne
    @JoinColumn(name = "source_state_id")
    private States sourceState;

    @ManyToOne
    @JoinColumn(name = "target_state_id")
    private States targetState;
}

