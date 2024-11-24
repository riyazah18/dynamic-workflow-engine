package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "META_ENTITY", schema = "dbo")
public class MetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meta_entity_seq_generator")
    @SequenceGenerator(name="meta_entity_seq_generator", sequenceName = "meta_entity_seq_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROCESS_CODE", nullable = false)
    private WfProcess wfProcess;

    @Column(name = "ENTITY_NAME_EN", length = 50)
    private String entityNameEn;

    @Column(name = "ENTITY_NAME_AR", length = 50)
    private String entityNameAr;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

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