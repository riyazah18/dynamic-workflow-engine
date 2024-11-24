package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_generator")
    @SequenceGenerator(name="task_seq_generator", sequenceName = "task_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "current_state_id", length = 30)
    private String currentStateId;

    @Column(name = "assignee_id", length = 30)
    private String assigneeId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskActivity> taskActivities = new ArrayList<>();
    @Transient
    private String previousStateId;

}