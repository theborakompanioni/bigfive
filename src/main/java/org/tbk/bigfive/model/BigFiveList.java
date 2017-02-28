package org.tbk.bigfive.model;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
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
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "bigint")
    }, inverseJoinColumns = {
            @JoinColumn(name = "list_item_id", referencedColumnName = "list_item_id", columnDefinition = "bigint")
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private List<BigFiveItem> items = Collections.emptyList();

    @JoinTable(name = "user_lists", joinColumns = {
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "bigint")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "bigint")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> user = Collections.emptyList();

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    protected BigFiveList() {
    }

    public BigFiveList(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "BigFiveList[id=%d, name='%s']",
                id, name);
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
}
