package org.tbk.bigfive.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "list_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.CHAR, columnDefinition = "default 'I'")
@DiscriminatorValue("I")
public class BigFiveItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "list_item_id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String description;

    @JoinTable(name = "list_items", joinColumns = {
            @JoinColumn(name = "list_item_id", referencedColumnName = "list_item_id", columnDefinition = "bigint")
    }, inverseJoinColumns = {
            @JoinColumn(name = "list_id", referencedColumnName = "list_id", columnDefinition = "bigint")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<BigFiveList> list = Collections.emptyList();

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    protected BigFiveItem() {
    }

    public BigFiveItem(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "BigFiveItem[id=%d, name='%s']",
                id, name);
    }

    public String getName() {
        return name;
    }
}
