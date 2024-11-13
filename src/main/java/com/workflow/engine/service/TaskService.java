package com.workflow.engine.service;

import com.workflow.engine.entity.*;
import com.workflow.engine.enums.NotificationType;
import com.workflow.engine.model.TaskRequestModel;
import com.workflow.engine.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final WorkflowTransitionRepository transitionRepository;
    private final WorkflowStateRepository workflowStateRepository;
    private final UserRepository userRepository;
    private final OrgUserRoleRepository orgUserRoleRepository;
    private final WfProcessParticipantRepository wfProcessParticipantRepository;

    public Task createTask(TaskRequestModel taskRequestModel) {
        Task task = new Task();
        task.setTitle(taskRequestModel.getTitle());
        task.setDescription(taskRequestModel.getDescription());
        task.setCreatedDate(Instant.from(taskRequestModel.getCreatedDate() != null
                ? taskRequestModel.getCreatedDate()
                : LocalDateTime.now()));
        task.setLastUpdatedDate(Instant.from(LocalDateTime.now()));

        // Fetch and set current state
        WfState currentState = workflowStateRepository.findById(taskRequestModel.getCurrentStateId())
                .orElseThrow(() -> new IllegalStateException("Invalid state ID"));
        if(Boolean.TRUE.equals(currentState.getIsStart())){
            task.setCurrentStateId(currentState.getId().toString());
        }
        // Fetch and set assignee
        User assignee = userRepository.findById(taskRequestModel.getAssigneeId())
                .orElseThrow(() -> new IllegalStateException("Invalid user ID"));
        task.setAssigneeId(assignee.getLoginId());

        return saveTask(task);
    }

    public Task moveTaskToNextState(Long taskId, String toStateId) throws IllegalStateException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found"));
        String currentState = task.getCurrentStateId();
        task.setPreviousStateId(currentState);
        String assigneeLoginId = task.getAssigneeId();

        // Check if assignee has the right process and states bound to his role
        OrgUserRole orgUserRole = getOrgUserRole(assigneeLoginId);

        // Retrieve the WfProcessParticipant for the assignee
        WfProcessParticipant processParticipant = getProcessParticipant(orgUserRole);

        // Get and use the process code
        String processCode = String.valueOf(processParticipant.getProcessCode());
       // check for end state
        WfState wfState = workflowStateRepository.getWfStatesByProcessCodeAndStateCode(processCode,toStateId)
                .orElseThrow(() -> new IllegalStateException("Invalid state ID"));
        if(Boolean.FALSE.equals(wfState.getIsEnd())){
            task.setCurrentStateId(wfState.getStateCode().toString());
        }
        // Check if transition is allowed
        WfTransition transition = getWfTransition(toStateId, currentState, processCode);

        task.setCurrentStateId(transition.getToState().getId());
        task.setLastUpdatedDate(Instant.from(LocalDateTime.now()));
        if (Boolean.TRUE.equals(transition.getIsNotificationEnable())) {
            sendNotification(transition.getNotificationType());
        }

        return saveTask(task);
    }

    private Task saveTask(Task task) {
        TaskActivity activity = new TaskActivity();
        activity.setPerformBy(task.getAssigneeId());
        activity.setAssigneeId("manager");
        activity.setPreviousStateId(task.getPreviousStateId());
        activity.setCurrentStateId(task.getCurrentStateId());
        activity.setComments("bjnsbasdbahddd");
        activity.setLastUpdatedDate(Instant.from(LocalDateTime.now()));
        activity.setCreatedDate(Instant.from(task.getCreatedDate()));

        task.getTaskActivities().add(activity);

        return taskRepository.save(task);
    }

    private WfTransition getWfTransition(String toStateId, String currentState, String processCode) {
        return transitionRepository
                .findByFromState_IdAndToState_IdAndProcessCode(currentState, toStateId, processCode)
                .orElseThrow(() -> new IllegalStateException("Invalid transition"));
    }

    private WfProcessParticipant getProcessParticipant(OrgUserRole orgUserRole) {
        return wfProcessParticipantRepository.findWfProcessParticipantByRoleId(orgUserRole.getRole())
                .orElseThrow(() -> new IllegalStateException("Process participant not found"));
    }

    private OrgUserRole getOrgUserRole(String assigneeLoginId) {
        return orgUserRoleRepository.findByLoginId(assigneeLoginId)
                .orElseThrow(() -> new IllegalStateException("Assignee not found"));
    }

    // New Method to handle notifications
    private void sendNotification(String notificationType) {
        NotificationType type = NotificationType.valueOf(notificationType.toUpperCase());
        switch (type) {
            case EMAIL:
                // send email
                break;
            case SMS:
                // send sms
                break;
            default:
                throw new IllegalArgumentException("Unknown notification type: " + notificationType);
        }
    }
}