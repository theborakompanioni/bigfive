package org.tbk.bigfive.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    @Convert(converter = CryptoConverter.class)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals = Collections.emptyList();

    /*@JoinTable(name = "user_lists", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "bigint")
    }, inverseJoinColumns = {
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "bigint")
    })*/
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="owner")
    private List<BigFiveList> lists = Collections.emptyList();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public List<BigFiveList> getLists() {
        return lists;
    }
}
