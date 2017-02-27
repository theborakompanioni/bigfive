package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "user", path = "user")
//@RepositoryRestResource(excerptProjection = UserWithListsProjection.class, collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByGoals(Goal goal);
}
