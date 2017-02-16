package org.tbk.bigfive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.tbk.bigfive.model.Goal;
import org.tbk.bigfive.model.GoalRepository;
import org.tbk.bigfive.model.User;
import org.tbk.bigfive.model.UserRepository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:bigfive.db");
        return dataSourceBuilder.build();
    }

    @Bean
    @Profile({"dev", "debug"})
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.debug("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.debug(beanName);
            }

        };
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, GoalRepository goalRepository) {
        return (args) -> {
            User user = new User("test", "", Collections.emptyList());
            userRepository.save(user);

            goalRepository.save(new Goal(user, "Goal1", "Description1"));
            goalRepository.save(new Goal(user, "Goal2", "Description2"));
            goalRepository.save(new Goal(user, "Goal3", "Description3"));
            goalRepository.save(new Goal(user, "Goal4", "Description4"));
            goalRepository.save(new Goal(user, "GOal5", "Description5"));

            log.info("Goals found with findAll():");
            log.info("-------------------------------");
            for (Goal goal : goalRepository.findAll()) {
                log.info(goal.toString());
            }
            log.info("");
        };
    }
}