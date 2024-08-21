package com.workflow.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.engine.entity.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
}
