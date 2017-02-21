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

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals = Collections.emptyList();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities = Collections.emptyList();

    protected User() {
    }

    public User(String name, String password, List<String> authorities) {
        this.name = requireNonNull(name);
        this.password = requireNonNull(password);
        this.authorities = requireNonNull(authorities);
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

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s']", id, name);
    }

}
