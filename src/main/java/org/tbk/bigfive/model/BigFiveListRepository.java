package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tbk.bigfive.model.projection.ListWithOwnerProjection;

import java.util.List;

//@RepositoryRestResource(excerptProjection = ListWithOwnerProjection.class, collectionResourceRel = "list", path = "list")
@RepositoryRestResource(collectionResourceRel = "list", path = "list")
public interface BigFiveListRepository extends JpaRepository<BigFiveList, Long> {

    List<BigFiveList> findByName(String name);

    List<BigFiveList> findByItems(BigFiveItem item);

    List<BigFiveList> findByOwner(User owner);

    List<BigFiveList> findByUser(User user);
}
