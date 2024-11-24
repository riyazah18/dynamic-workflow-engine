package com.workflow.engine.repository;

import com.workflow.engine.entity.WfTransition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkflowTransitionRepository extends JpaRepository<WfTransition, Long> {
    Optional<WfTransition> findByFromState_IdAndToState_IdAndProcessCode(Long fromStateId, Long toStateId, String processCode);
}