package org.tbk.bigfive.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoalRepository extends CrudRepository<Goal, Long> {

    List<Goal> findByName(String name);
}