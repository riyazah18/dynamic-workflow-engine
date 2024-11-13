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
public class WfGlobalState {
    @Id
    @Column(name = "ID", nullable = false, length = 30)
    private String id;

    @Column(name = "NAME_EN", nullable = false, length = 100)
    private String nameEn;

    @Column(name = "NAME_AR", nullable = false, length = 100)
    private String nameAr;

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