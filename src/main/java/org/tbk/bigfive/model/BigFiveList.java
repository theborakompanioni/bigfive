package org.tbk.bigfive.model;

import com.google.common.collect.ImmutableList;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "list")
public class BigFiveList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "list_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String description;

    @JoinTable(name = "list_items", joinColumns = {
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "integer")
    }, inverseJoinColumns = {
            @JoinColumn(name = "list_item_id", referencedColumnName = "list_item_id", columnDefinition = "integer")
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private List<BigFiveItem> items = Collections.emptyList();

    @JoinTable(name = "user_lists", joinColumns = {
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "integer")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "integer")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> user = Collections.emptyList();

    protected BigFiveList() {
    }

    @Override
    public String toString() {
        return String.format(
                "BigFiveList[id=%d, name='%s']",
                id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<BigFiveItem> getItems() {
        return ImmutableList.copyOf(items);
    }

    public void setItems(List<BigFiveItem> items) {
        this.items = items == null ? Collections.emptyList() : ImmutableList.copyOf(items);
    }

    public List<User> getUser() {
        return ImmutableList.copyOf(user);
    }

    public void setUser(List<User> user) {
        this.user = user == null ? Collections.emptyList() : ImmutableList.copyOf(user);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
