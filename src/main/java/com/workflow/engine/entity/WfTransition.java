package com.workflow.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "WF_TRANSITION", schema = "dbo")
public class WfTransition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "PROCESS_CODE", nullable = false, length = 25)
    private String processCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FROM_STATE_ID", nullable = false)
    private WfGlobalState fromState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TO_STATE_ID", nullable = false)
    private WfGlobalState toState;

    @Column(name = "IS_NOTIFICATION_ENABLE", nullable = false)
    private Boolean isNotificationEnable = false;

    @Column(name = "NOTIFICATION_TYPE", length = 30)
    private String notificationType;

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