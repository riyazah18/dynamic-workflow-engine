package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
public class OrgRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wf_org_role_seq_generator")
    @SequenceGenerator(name="wf_org_role_seq_generator", sequenceName = "wf_org_role_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ROLE_NAME_EN", nullable = false)
    private String roleNameEn;

    @Column(name = "ROLE_NAME_AR", nullable = false)
    private String roleNameAr;

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