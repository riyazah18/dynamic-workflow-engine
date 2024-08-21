package com.workflow.engine.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.workflow.engine.entity.States;
import com.workflow.engine.entity.Transition;
import com.workflow.engine.repository.StateRepository;
import com.workflow.engine.repository.TransitionRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final StateRepository stateRepository;
    private final TransitionRepository transitionRepository;

    public DataLoader(StateRepository stateRepository, TransitionRepository transitionRepository) {
        this.stateRepository = stateRepository;
        this.transitionRepository = transitionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert states
        States state1 = new States();
        state1.setName("STATE1");
        state1.setInitial(true);
        state1.setFinal(false);
        stateRepository.save(state1);

        States state2 = new States();
        state2.setName("STATE2");
        state2.setInitial(false);
        state1.setFinal(true);
        stateRepository.save(state2);

        // Retrieve states from database
        state1 = stateRepository.findByName("STATE1");
        state2 = stateRepository.findByName("STATE2");

        // Insert transitions
        Transition transition = new Transition();
        transition.setSourceState(state1);
        transition.setTargetState(state2);
        transition.setEvent("SUBMIT");
        transitionRepository.save(transition);
    }
}

