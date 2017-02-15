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

import javax.sql.DataSource;
import java.util.Arrays;

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
    public CommandLineRunner demo(GoalRepository repository) {
        return (args) -> {
            repository.save(new Goal("Jack", "Bauer"));
            repository.save(new Goal("Chloe", "O'Brian"));
            repository.save(new Goal("Kim", "Bauer"));
            repository.save(new Goal("David", "Palmer"));
            repository.save(new Goal("Michelle", "Dessler"));

            log.info("Goals found with findAll():");
            log.info("-------------------------------");
            for (Goal customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            Goal customer = repository.findOne(1L);
            log.info("Goal found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            log.info("Goal found with findByName('Chloe'):");
            log.info("--------------------------------------------");
            for (Goal bauer : repository.findByName("Chloe")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}