package com.workflow.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.engine.entity.Transition;

public interface TransitionRepository extends JpaRepository<Transition, Long> {
}

