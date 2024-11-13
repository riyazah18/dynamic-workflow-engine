package com.workflow.engine.repository;

import com.workflow.engine.entity.WfProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<WfProcess, Long> {
}