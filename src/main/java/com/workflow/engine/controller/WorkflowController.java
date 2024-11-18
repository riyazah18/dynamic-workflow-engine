package com.workflow.engine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.workflow.engine.entity.WfProcess;
import com.workflow.engine.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;

    @GetMapping
    @JsonView(value = Object.class)
    public List<WfProcess> getAllWorkflows() {
        return workflowService.getAllWorkflows();
    }

    @PostMapping
    @JsonView(value = Object.class)
    public WfProcess createWorkflow(@RequestBody WfProcess workflow) {
        return workflowService.createWorkflow(workflow);
    }

    @GetMapping("/{id}")
    @JsonView(value = Object.class)
    public WfProcess getWorkflowById(@PathVariable Long id) {
        return workflowService.getWorkflowById(id);
    }

    @PutMapping("/{id}")
    @JsonView(value = Object.class)
    public WfProcess updateWorkflow(@PathVariable Long id, @RequestBody WfProcess workflow) {
        return workflowService.updateWorkflow(id, workflow);
    }

    @DeleteMapping("/{id}")
    @JsonView(value = Object.class)
    public void deleteWorkflow(@PathVariable Long id) {
        workflowService.deleteWorkflow(id);
    }

    // Other endpoint methods for WorkflowState, User, Task, WorkflowTransition
    // Add similar methods for other entities
}
