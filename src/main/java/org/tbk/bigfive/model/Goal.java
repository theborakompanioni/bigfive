package org.tbk.bigfive.model;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Data
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goal_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String description;

    @JoinTable(name = "user_goals", joinColumns = {
            @JoinColumn(name = "goal_id", referencedColumnName = "goal_id", columnDefinition = "bigint")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "bigint")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> user;

    protected Goal() {
    }

    public Goal(User user, String name, String description) {
        this.user = Lists.newArrayList(user);
        this.name = requireNonNull(name);
        this.description = description;
    }

    public User getUser() {
        return Optional.ofNullable(user)
                .flatMap(user -> user.stream().findFirst())
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format(
                "Goal[id=%d, name='%s', user=%s]",
                id, name, user);
    }
}
