package com.workflow.engine.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.engine.entity.States;

public interface StateRepository extends JpaRepository<States, Long> {
     States findByName(String name);
}

