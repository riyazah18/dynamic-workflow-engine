package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
public class WfState {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wf_state_seq_generator")
    @SequenceGenerator(name="wf_state_seq_generator", sequenceName = "wf_state_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROCESS_CODE", nullable = false)
    private WfProcess processCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATE_CODE", nullable = false)
    private WfGlobalState stateCode;

    @Column(name = "IS_START", nullable = false)
    private Boolean isStart = false;

    @Column(name = "IS_END", nullable = false)
    private Boolean isEnd = false;

    @Column(name = "IS_ADHOC", nullable = false)
    private Boolean isAdhoc = false;

    @Column(name = "IS_PARKED", nullable = false)
    private Boolean isParked = false;

    @Column(name = "CREATED_ON", nullable = false)
    private Instant createdOn;

    @Nationalized
    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "UPDATED_ON", nullable = false)
    private Instant updatedOn;

    @Nationalized
    @Column(name = "UPDATED_BY", length = 60)
    private String updatedBy;

}