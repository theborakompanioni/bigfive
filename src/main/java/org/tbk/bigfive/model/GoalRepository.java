package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "goal", path = "goal")
public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByName(String name);

    List<Goal> findByUser(User user);
}
