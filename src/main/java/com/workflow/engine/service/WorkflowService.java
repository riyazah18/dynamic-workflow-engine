package com.workflow.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import com.workflow.engine.entity.Contract;
import com.workflow.engine.repository.ContractRepository;

@Service
public class WorkflowService {
    @Autowired
    private StateMachine<String, String> stateMachine;

    @Autowired
    private ContractRepository contractRepository;

    public void submitContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();
        
        // Assuming "SUBMIT" is your event identifier
        boolean eventSent = stateMachine.sendEvent("SUBMIT");

        if (eventSent) {
            // Get the current state as a String
            String currentState = stateMachine.getState().getId();
            
            // Update the contract's state
            contract.setState(currentState);
            contractRepository.save(contract);
        } else {
            // Handle the case where the event could not be sent
            throw new IllegalStateException("Event could not be sent");
        }
    }
}
