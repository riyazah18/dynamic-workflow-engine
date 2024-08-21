package com.workflow.engine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.workflow.engine.entity.States;
import com.workflow.engine.entity.Transition;
import com.workflow.engine.repository.StateRepository;
import com.workflow.engine.repository.TransitionRepository;

import java.util.List;

@Configuration
@EnableStateMachine
public class ContractStateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TransitionRepository transitionRepository;

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        // Fetch states from the database
        List<States> dbStates = stateRepository.findAll();
        
        // Configure each state dynamically
        var stateConfigurer = states.withStates();

        for (States state : dbStates) {
            if (state.isInitial()) {
                stateConfigurer.initial(state.getName());
            }
            stateConfigurer.state(state.getName());
        }
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        // Fetch transitions from the database
        List<Transition> dbTransitions = transitionRepository.findAll();  

        // Configure each transition dynamically
        for (Transition transition : dbTransitions) {
            transitions
                .withExternal()
                .source(transition.getSourceState().getName())
                .target(transition.getTargetState().getName())
                .event(transition.getEvent());            
        }
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config
            .withConfiguration()
            .autoStartup(true);  // Optional: Configure auto startup
    }
}
