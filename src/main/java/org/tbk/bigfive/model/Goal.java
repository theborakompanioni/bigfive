package org.tbk.bigfive.model;

import javax.persistence.*;

@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    protected Goal() {
    }

    public Goal(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Goal[id=%d, name='%s']",
                id, name);
    }

}