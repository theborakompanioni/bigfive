package org.tbk.bigfive.model;

import io.reactivex.Observable;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Entity()
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    @Convert(converter = CryptoConverter.class)
    private String password;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Goal> goals;

    protected User() {
    }

    public User(String name, String password, List<Goal> goals) {
        this.name = requireNonNull(name);
        this.password = password;
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Observable<Goal> getGoalsObservable() {
        return Observable.fromIterable(
                Optional.ofNullable(goals)
                        .orElse(Collections.emptyList())
        );
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s']", id, name);
    }
}