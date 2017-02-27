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
public class BigFiveItemRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BigFiveItemRepository sut;

    @Test
    public void save() throws Exception {
        User user1 = new User("test", "", Collections.emptyList());
        userRepository.save(user1);

        final BigFiveItem item = new BigFiveItem(user1, "test1");

        sut.saveAndFlush(item);

        final BigFiveItem findOne = sut.findOne(item.getId());
        assertThat(findOne, is(notNullValue()));
    }


    @Test
    public void findByOwner() throws Exception {
        User user1 = new User("test", "", Collections.emptyList());
        User user2 = new User("test2", "", Collections.emptyList());

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);

        final BigFiveItem item = new BigFiveItem(user1, "test1");

        sut.saveAndFlush(item);

        final List<BigFiveItem> byUser = sut.findByOwner(user1);
        assertThat(byUser, hasSize(greaterThan(0)));
    }

}
