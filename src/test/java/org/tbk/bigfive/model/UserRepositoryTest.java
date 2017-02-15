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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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

    @Test
    public void findByName() throws Exception {
        User user1 = new User("test");
        User user2 = new User("test2");

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);

        final List<User> byName = userRepository.findByName(user2.getName());
        assertThat(byName, hasSize(1));
    }

}