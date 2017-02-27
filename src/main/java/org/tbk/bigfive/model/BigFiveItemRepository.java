package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface BigFiveItemRepository extends JpaRepository<BigFiveItem, Long> {

    List<BigFiveItem> findByName(String name);

    List<BigFiveItem> findByOwner(User owner);

    List<BigFiveItem> findByList(BigFiveList list);
}
