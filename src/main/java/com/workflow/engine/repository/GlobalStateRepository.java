package com.workflow.engine.repository;

import com.workflow.engine.entity.WfGlobalState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalStateRepository extends JpaRepository<WfGlobalState, String> {
}