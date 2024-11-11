package com.workflow.engine.service;

import com.workflow.engine.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    public List<Process> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    public Process createWorkflow(Process process) {
        return workflowRepository.save(process);
    }

    public Process getWorkflowById(Long id) {
        return workflowRepository.findById(id).orElse(null);
    }

    public Process updateWorkflow(Long id, Process workflow) {
        if (workflowRepository.existsById(id)) {
            workflow.setProcessCode(String.valueOf(id));
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
