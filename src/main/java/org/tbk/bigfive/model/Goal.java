package org.tbk.bigfive.model;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String description;

    protected Goal() {
    }

    public Goal(User user, String name, String description) {
        this.user = user;
        this.name = requireNonNull(name);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
                "Goal[id=%d, name='%s', user=%s]",
                id, name, user);
    }

}