package com.workflow.engine.service;

import com.workflow.engine.entity.*;
import com.workflow.engine.enums.NotificationType;
import com.workflow.engine.model.TaskRequestModel;
import com.workflow.engine.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final WorkflowTransitionRepository transitionRepository;
    private final WorkflowStateRepository workflowStateRepository;
    private final UserRepository userRepository;
    private final OrgUserRoleRepository orgUserRoleRepository;
    private final WfProcessParticipantRepository wfProcessParticipantRepository;
    private final GlobalStateRepository globalStateRepository;

    public Task createTask(TaskRequestModel taskRequestModel) {
        Task task = new Task();
        task.setTitle(taskRequestModel.getTitle());
        task.setDescription(taskRequestModel.getDescription());
        task.setCreatedDate(taskRequestModel.getCreatedDate() != null
                ? taskRequestModel.getCreatedDate().toInstant(ZoneOffset.UTC)
                : LocalDateTime.now().toInstant(ZoneOffset.UTC));
        task.setLastUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        // Fetch and set current state
        loadAndSetCurrentState(task, taskRequestModel.getInitialState());

// Fetch process code from WfState
        WfState wfState = workflowStateRepository.getWfStates(
                String.valueOf(taskRequestModel.getInitialState())).orElseThrow(() -> new IllegalStateException("Invalid state ID"));
        WfProcess processCode = wfState.getProcessCode();

// Fetch role from WfProcessParticipant with the fetched process
        WfProcessParticipant processParticipant = wfProcessParticipantRepository.findWfProcessParticipantByWfProcess(processCode)
                .orElseThrow(() -> new IllegalStateException("Process participant not found"));
        OrgRole role = processParticipant.getRole();
           User currentActor = getUser(role);
// Assuming you need to set the fetched user as the assignee of the task
        task.setAssigneeId(currentActor.getLoginId());

        return saveTask(task);
    }
    // Fetch the user from OrgUserRole with the fetched role from the participant table
    private User getUser(OrgRole role) {
        return getLoginByRoleId(role).getUser();
    }

    // Extracted method to get login by role ID
    private OrgUserRole getLoginByRoleId(OrgRole role) {
        return orgUserRoleRepository.findOrgUserRoleByRole(role)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void loadAndSetCurrentState(Task task, Long currentStateId) {
        Optional<WfGlobalState> globalState = globalStateRepository.findById(String.valueOf(currentStateId));
        if (!globalState.isPresent()) {
            throw new IllegalStateException("Invalid state ID");
        }
        WfState currentState = fetchCurrentState(globalState.get());

        if (Boolean.TRUE.equals(currentState.getIsStart())) {
            task.setCurrentStateId(currentState.getStateCode().getId().toString());
        }
    }

    private WfState fetchCurrentState(WfGlobalState currentStateId) {
        return workflowStateRepository.findWfStatesByStateCode(currentStateId)
                .orElseThrow(() -> new IllegalStateException("Invalid state ID"));
    }

    public Task moveTaskToNextState(Long taskId, Long toStateId) throws IllegalStateException {
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
        String processCode = String.valueOf(processParticipant.getWfProcess());
        // check for end state
        WfState wfState = workflowStateRepository.getWfStatesByProcessCodeAndStateCode(processCode, toStateId)
                .orElseThrow(() -> new IllegalStateException("Invalid state ID"));
        if (Boolean.FALSE.equals(wfState.getIsEnd())) {
            task.setCurrentStateId(wfState.getStateCode().toString());
        }
        // Check if transition is allowed
        WfTransition transition = getWfTransition(toStateId, Long.valueOf(currentState), processCode);

        task.setCurrentStateId(transition.getToState().getId().toString());
        task.setLastUpdatedDate(Instant.from(LocalDateTime.now()));
        if (Boolean.TRUE.equals(transition.getIsNotificationEnable())) {
            sendNotification(transition.getNotificationType());
        }

        return saveTask(task);
    }

    private Task saveTask(Task task) {
        TaskActivity activity = new TaskActivity();
        activity.setPerformBy(task.getAssigneeId());
        activity.setAssigneeId("next assignee");
        activity.setPreviousStateId(task.getPreviousStateId());
        activity.setCurrentStateId(task.getCurrentStateId());
        activity.setComments("bjnsbasdbahddd");
        activity.setCreatedDate(task.getCreatedDate());
        // Set the task property
        activity.setTask(task);
        task.getTaskActivities().add(activity);

        return taskRepository.save(task);
    }

    private WfTransition getWfTransition(Long toStateId, Long currentState, String processCode) {
        return transitionRepository
                .findByFromState_IdAndToState_IdAndProcessCode(currentState, toStateId, processCode)
                .orElseThrow(() -> new IllegalStateException("Invalid transition"));
    }

    private WfProcessParticipant getProcessParticipant(OrgUserRole orgUserRole) {
        return wfProcessParticipantRepository.findWfProcessParticipantByRole(orgUserRole.getRole())
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