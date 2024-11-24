package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "TASK_ACTIVITY", schema = "dbo")
public class TaskActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_activity_seq_generator")
    @SequenceGenerator(name="task_activity_seq_generator", sequenceName = "task_activity_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;

    @Lob
    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @Column(name = "PREVIOUS_STATE_ID", length = 30)
    private String previousStateId;

    @Column(name = "CURRENT_STATE_ID", length = 30)
    private String currentStateId;

    @Column(name = "PERFORM_BY", length = 30)
    private String performBy;

    @Column(name = "ASSIGNEE_ID", length = 30)
    private String assigneeId;

}