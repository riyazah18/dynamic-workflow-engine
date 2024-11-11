package com.workflow.engine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ORG_ROLE", schema = "dbo")
public class OrgRole {
    @Id
    @Column(name = "ID", nullable = false, length = 25)
    private String id;

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