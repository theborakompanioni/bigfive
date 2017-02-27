package org.tbk.bigfive.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.tbk.bigfive.model.BigFiveItemRepository;
import org.tbk.bigfive.model.BigFiveListRepository;
import org.tbk.bigfive.model.GoalRepository;
import org.tbk.bigfive.model.UserRepository;

@Configuration
public class DemoConfig {

    @Bean
    public DemoService demoService(PasswordEncoder passwordEncoder,
                                   UserRepository userRepository,
                                   GoalRepository goalRepository,
                                   BigFiveListRepository listRepository,
                                   BigFiveItemRepository itemRepository) {
        return new DemoService(passwordEncoder,
                userRepository,
                goalRepository,
                listRepository,
                itemRepository);
    }

    @Bean
    public CommandLineRunner demo(DemoService demoService) {
        return new CommandLineRunner() {

            @Override
            @Transactional
            public void run(String... args) throws Exception {
                demoService.printGoalsOfDemoUser();
                demoService.createDemoLists();
            }
        };
    }
}
