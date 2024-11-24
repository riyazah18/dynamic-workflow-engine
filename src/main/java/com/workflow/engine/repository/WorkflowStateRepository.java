package com.workflow.engine.repository;

import com.workflow.engine.entity.WfGlobalState;
import com.workflow.engine.entity.WfState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowStateRepository extends JpaRepository<WfState, Long> {
    @Query("SELECT wfs FROM WfState wfs WHERE wfs.processCode = ?1 AND wfs.stateCode = ?2")
    Optional<WfState> getWfStatesByProcessCodeAndStateCode(String processCode, Long currentState);

    Optional<WfState> findWfStatesByStateCode(WfGlobalState currentStateId);

    @Query("""
      SELECT wfs FROM WfState wfs  
      WHERE wfs.stateCode.id =:initialState
      
      """)
    Optional<WfState> getWfStates(String initialState);
}