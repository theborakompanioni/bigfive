package org.tbk.bigfive.demo;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tbk.bigfive.model.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final BigFiveListRepository listRepository;
    private final BigFiveItemRepository itemRepository;

    private final Supplier<DemoUser.DemoUserBuilder> demoUserBuilderSupplier = Suppliers
            .memoize(this::createDemoUserBuilder);

    public DemoService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       GoalRepository goalRepository,
                       BigFiveListRepository listRepository,
                       BigFiveItemRepository itemRepository) {
        this.passwordEncoder = requireNonNull(passwordEncoder);
        this.userRepository = requireNonNull(userRepository);
        this.goalRepository = requireNonNull(goalRepository);
        this.listRepository = requireNonNull(listRepository);
        this.itemRepository = requireNonNull(itemRepository);
    }

    public void printGoalsOfDemoUser() {
        User demoUser = this.getOrCreateDemoUser().getOrigin();

        log.info("Goals of demo user:");
        log.info("-------------------------------");
        goalRepository.findByUser(demoUser).stream()
                .map(Goal::toString)
                .forEach(log::info);
        log.info("");
    }

    public DemoUser getOrCreateDemoUser() {
        final User user = userRepository.findByName("demo")
                .stream()
                .findFirst()
                .orElseGet(() -> createDemoUser().getOrigin());

        return demoUserBuilderSupplier.get()
                .origin(user)
                .build();
    }

    private DemoUser createDemoUser() {
        final DemoUser.DemoUserBuilder demoUserBuilder = demoUserBuilderSupplier.get();
        final ArrayList<String> authorities = Lists.newArrayList("ROLE_ADMIN", "ROLE_USER");

        User demoUser = new User(demoUserBuilder.name(), demoUserBuilder.encryptedPassword(), authorities);

        userRepository.save(demoUser);
        log.info("Created demo user: {}", demoUser);

        goalRepository.save(new Goal(demoUser, "Goal1", "Description1"));
        goalRepository.save(new Goal(demoUser, "Goal2", "Description2"));
        goalRepository.save(new Goal(demoUser, "Goal3", "Description3"));
        goalRepository.save(new Goal(demoUser, "Goal4", "Description4"));
        goalRepository.save(new Goal(demoUser, "Goal5", "Description5"));

        return demoUserBuilder
                .origin(demoUser)
                .build();
    }

    private DemoUser.DemoUserBuilder createDemoUserBuilder() {
        final String username = "demo";
        final String password = "demo";
        return DemoUser.builder()
                .name(username)
                .password(password)
                .encryptedPassword(passwordEncoder.encode(password));
    }


    public void createDemoLists() {
        User demoUser = getOrCreateDemoUser().getOrigin();
        final boolean demoUserHasItems = !demoUser.getLists().isEmpty();

        if (!demoUserHasItems) {
            BigFiveItem item1 = new BigFiveItem(demoUser, "item" + RandomStringUtils.randomAscii(5));
            BigFiveItem item2 = new BigFiveItem(demoUser, "item" + RandomStringUtils.randomAscii(5));
            BigFiveItem item3 = new BigFiveItem(demoUser, "item" + RandomStringUtils.randomAscii(5));
            BigFiveItem item4 = new BigFiveItem(demoUser, "item" + RandomStringUtils.randomAscii(5));
            BigFiveItem item5 = new BigFiveItem(demoUser, "item" + RandomStringUtils.randomAscii(5));

            List<BigFiveItem> items = Lists.newArrayList(item1, item2, item3, item4, item5);
            itemRepository.save(items);

            BigFiveList list1 = new BigFiveList(demoUser, "list" + RandomStringUtils.randomAscii(5));
            list1.setItems(Lists.newArrayList(item1, item2, item3, item4, item5));

            BigFiveList list2 = new BigFiveList(demoUser, "list" + RandomStringUtils.randomAscii(5));
            list2.setItems(Lists.newArrayList(item1, item2, item3, item4));

            BigFiveList list3 = new BigFiveList(demoUser, "list" + RandomStringUtils.randomAscii(5));
            list3.setItems(Lists.newArrayList(item1, item2, item3));

            BigFiveList list4 = new BigFiveList(demoUser, "list" + RandomStringUtils.randomAscii(5));
            list4.setItems(Lists.newArrayList(item1, item5));

            BigFiveList list5 = new BigFiveList(demoUser, "list" + RandomStringUtils.randomAscii(5));
            list5.setItems(Lists.newArrayList(item2, item3, item5));

            List<BigFiveList> lists = Lists.newArrayList(list1, list2, list3, list4, list5);

            listRepository.save(lists);
        }
    }

}
