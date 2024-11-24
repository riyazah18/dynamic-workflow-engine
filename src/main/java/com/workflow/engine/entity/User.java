package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "[user]") // Escaping the table name 'user'
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wf_user_seq_generator")
    @SequenceGenerator(name="wf_user_seq_generator", sequenceName = "wf_user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "LOGIN_ID", nullable = false, length = 25)
    private String loginId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

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