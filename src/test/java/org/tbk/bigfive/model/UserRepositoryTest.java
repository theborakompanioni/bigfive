package org.tbk.bigfive.model;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.tbk.bigfive.config.TestDbConfig;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TestDbConfig.class
})
@Import(TestDbConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@Rollback
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Test
    public void findByName() throws Exception {
        User user1 = new User("test", "", Collections.emptyList());
        User user2 = new User("test2", "", Collections.emptyList());

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);

        final List<User> byName = userRepository.findByName(user2.getName());
        assertThat(byName, hasSize(greaterThan(0)));
    }

    @Test
    public void findByGoal() throws Exception {
        User user1 = new User("test", "", Collections.emptyList());
        User user2 = new User("test2", "", Collections.emptyList());

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);

        Goal goal1 = new Goal(user1, "goal1", "description1");
        goalRepository.save(goal1);

        final List<User> byGoals = userRepository.findByGoals(goal1);
        assertThat(byGoals, hasItem(equalTo(user1)));
    }

}