package com.workflow.engine.repository;

import com.workflow.engine.entity.WorkflowTransition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkflowTransitionRepository extends JpaRepository<WorkflowTransition, Long> {
    Optional<WorkflowTransition> findByFromState_IdAndToState_Id(Long id, Long toStateId);
}