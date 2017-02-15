package org.tbk.bigfive.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GoalRepository extends PagingAndSortingRepository<Goal, Long> {

    List<Goal> findByName(String name);

    List<Goal> findByUser(User user);
}