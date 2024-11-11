package com.workflow.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalStateRepository extends JpaRepository<GlobalState, Long> {
}