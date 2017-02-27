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
import org.springframework.transaction.support.TransactionTemplate;
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
public class BigFiveListRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BigFiveItemRepository itemRepository;

    @Autowired
    private BigFiveListRepository sut;

    @Test
    public void save() throws Exception {
        final BigFiveList bigFiveList = new BigFiveList();
        bigFiveList.setName("test1");

        sut.saveAndFlush(bigFiveList);

        final BigFiveList findOne = sut.findOne(bigFiveList.getId());
        assertThat(findOne, is(notNullValue()));
    }

    @Test
    public void findByName() throws Exception {
        final BigFiveList bigFiveList = new BigFiveList();
        bigFiveList.setName("test1");
        sut.saveAndFlush(bigFiveList);

        final List<BigFiveList> byName = sut.findByName(bigFiveList.getName());
        assertThat(byName, hasSize(greaterThan(0)));
    }


    @Test
    public void findByUser() throws Exception {
        User user1 = new User("test", "", Collections.emptyList());
        User user2 = new User("test2", "", Collections.emptyList());

        final List<User> users = Lists.newArrayList(user1, user2);
        userRepository.save(users);

        final BigFiveList bigFiveList = new BigFiveList();
        bigFiveList.setName("test1");
        bigFiveList.setUser(users);

        sut.saveAndFlush(bigFiveList);

        final List<BigFiveList> byUser = sut.findByUser(user1);
        assertThat(byUser, hasSize(greaterThan(0)));
    }

    @Test
    public void findByItem() throws Exception {
        BigFiveItem item1 = new BigFiveItem();
        item1.setName("item1");
        BigFiveItem item2 = new BigFiveItem();
        item2.setName("item2");

        final List<BigFiveItem> items = Lists.newArrayList(item1, item2);

        itemRepository.save(items);

        final BigFiveList bigFiveList = new BigFiveList();
        bigFiveList.setName("test1");
        bigFiveList.setItems(items);

        sut.saveAndFlush(bigFiveList);

        final List<BigFiveList> byUser = sut.findByItems(item1);
        assertThat(byUser, hasSize(greaterThan(0)));
    }

}
