package com.workflow.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.engine.service.WorkflowService;

@RestController
public class ContractController {

    @Autowired
    private WorkflowService contractWorkflowService;

    @PostMapping("/contracts/{id}/submit")
    public ResponseEntity<?> submitContract(@PathVariable Long id) {
        contractWorkflowService.submitContract(id);
        return ResponseEntity.ok().build();
    }
}

