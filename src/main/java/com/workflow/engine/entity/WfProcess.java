package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
public class WfProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wf_process_seq_generator")
    @SequenceGenerator(name="wf_process_seq_generator", sequenceName = "wf_process_seq", allocationSize = 1)
    private Long id;

    @Column(name = "PROCESS_NAME_EN", nullable = false)
    private String processNameEn;

    @Column(name = "PROCESS_NAME_AR", nullable = false)
    private String processNameAr;

    @Column(name = "PROCESS_TYPE", nullable = false, precision = 2)
    private BigDecimal processType;

    @Column(name = "EFFECTIVE_FROM", nullable = false)
    private Instant effectiveFrom;

    @Column(name = "EFFECTIVE_TO", nullable = false)
    private Instant effectiveTo;

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