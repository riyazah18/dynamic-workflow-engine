package com.workflow.engine.service;

import com.workflow.engine.entity.Workflow;
import com.workflow.engine.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    public Workflow createWorkflow(Workflow workflow) {
        return workflowRepository.save(workflow);
    }

    public Workflow getWorkflowById(Long id) {
        return workflowRepository.findById(id).orElse(null);
    }

    public Workflow updateWorkflow(Long id, Workflow workflow) {
        if (workflowRepository.existsById(id)) {
            workflow.setId(id);
            return workflowRepository.save(workflow);
        } else {
            return null;
        }
    }

    public void deleteWorkflow(Long id) {
        workflowRepository.deleteById(id);
    }
    
    // Add similar methods for other entities
}
