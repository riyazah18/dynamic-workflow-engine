package com.workflow.engine.repository;

import com.workflow.engine.entity.OrgRole;
import com.workflow.engine.entity.WfProcessParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WfProcessParticipantRepository extends JpaRepository<WfProcessParticipant, Long> {

    Optional<WfProcessParticipant> findWfProcessParticipantByRoleId(OrgRole role);
}