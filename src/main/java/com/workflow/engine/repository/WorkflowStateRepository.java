package com.workflow.engine.repository;

import com.workflow.engine.entity.WfState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowStateRepository extends JpaRepository<WfState, Long> {
    @Query("SELECT w FROM WfState w WHERE w.processCode = ?1 AND w.stateCode = ?2")
    Optional<WfState> getWfStatesByProcessCodeAndStateCode(String processCode, String currentState);
}