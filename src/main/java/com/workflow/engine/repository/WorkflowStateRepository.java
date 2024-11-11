package com.workflow.engine.repository;

import com.workflow.engine.entity.WfState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStateRepository extends JpaRepository<WfState, Long> {
}