package org.tbk.bigfive.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tbk.bigfive.model.Goal;
import org.tbk.bigfive.model.GoalRepository;
import org.tbk.bigfive.model.User;
import org.tbk.bigfive.model.UserRepository;

import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    public DemoService(PasswordEncoder passwordEncoder, UserRepository userRepository, GoalRepository goalRepository) {
        this.passwordEncoder = requireNonNull(passwordEncoder);
        this.userRepository = requireNonNull(userRepository);
        this.goalRepository = requireNonNull(goalRepository);
    }

    public void printGoalsOfDemoUser() {
        User demoUser = this.getOrCreateDemoUser();

        log.info("Goals of demo user:");
        log.info("-------------------------------");
        goalRepository.findByUser(demoUser).stream()
                .map(Goal::toString)
                .forEach(log::info);
        log.info("");
    }
    
    public User getOrCreateDemoUser() {
        return userRepository.findByName("demo")
                .stream()
                .findFirst()
                .orElseGet(this::createDemoUser);
    }

    private User createDemoUser() {
        User demoUser = new User("demo", passwordEncoder.encode("demo"), Collections.emptyList());

        userRepository.save(demoUser);
        log.info("Created demo user: {}", demoUser);

        goalRepository.save(new Goal(demoUser, "Goal1", "Description1"));
        goalRepository.save(new Goal(demoUser, "Goal2", "Description2"));
        goalRepository.save(new Goal(demoUser, "Goal3", "Description3"));
        goalRepository.save(new Goal(demoUser, "Goal4", "Description4"));
        goalRepository.save(new Goal(demoUser, "GOal5", "Description5"));

        return demoUser;
    }

}
