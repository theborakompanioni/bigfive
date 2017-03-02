package org.tbk.bigfive.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "goal", path = "goal")
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Page<Goal> findByName(@Param("name") String name, Pageable page);

    Page<Goal> findByUser(User user, Pageable page);
}
