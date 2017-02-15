package org.tbk.bigfive.model;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.tbk.bigfive.config.TestDbConfig;

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
public class GoalRepositoryTest {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    User user1;
    User user2;

    List<User> users;
    Goal goal1;
    Goal goal2;

    List<Goal> goals;

    @Before
    public void setUp() {
        this.user1 = new User("test");
        this.user2 = new User("test2");

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);
        this.users = users;

        this.goal1 = new Goal(user1, "goal1", "description1");
        this.goal2 = new Goal(user2, "goal2", "description2");

        final List<Goal> goals = Lists.newArrayList(goal1, goal2);
        goalRepository.save(goals);
        this.goals = goals;
    }

    @Test
    public void findOne() throws Exception {
        Goal findOne = goalRepository.findOne(1L);
        assertThat(findOne, is(notNullValue()));
        assertThat(findOne.getName(), is(equalTo(goal1.getName())));
    }

    @Test
    public void findByName() throws Exception {
        final Page<Goal> findAll = goalRepository.findAll(new PageRequest(0, 10));
        final long totalElements = findAll.getTotalElements();
        assertThat(totalElements, is((long) goals.size()));
    }

    @Test
    public void findByUser() throws Exception {
        final List<Goal> byUser = goalRepository.findByUser(user1);
        assertThat(byUser, hasSize(1));
    }

}