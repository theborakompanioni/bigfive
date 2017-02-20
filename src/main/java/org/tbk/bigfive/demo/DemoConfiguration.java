package org.tbk.bigfive.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tbk.bigfive.model.GoalRepository;
import org.tbk.bigfive.model.UserRepository;

@Configuration
public class DemoConfiguration {

    @Bean
    public DemoService demoService(PasswordEncoder passwordEncoder, UserRepository userRepository, GoalRepository goalRepository) {
        return new DemoService(passwordEncoder, userRepository, goalRepository);
    }
    
}
