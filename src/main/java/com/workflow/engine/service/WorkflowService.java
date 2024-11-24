package com.workflow.engine.service;

import com.workflow.engine.entity.WfProcess;
import com.workflow.engine.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    public List<WfProcess> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    public WfProcess createWorkflow(WfProcess process) {
        return workflowRepository.save(process);
    }

    public WfProcess getWorkflowById(Long id) {
        return workflowRepository.findById(id).orElse(null);
    }

    public WfProcess updateWorkflow(Long id, WfProcess process) {
        if (workflowRepository.existsById(id)) {
            return workflowRepository.save(process);
        } else {
            return null;
        }
    }

    public void deleteWorkflow(Long id) {
        workflowRepository.deleteById(id);
    }

    // Add similar methods for other entities
}
