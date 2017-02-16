package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByName(String name);

    List<Goal> findByUser(User user);
}