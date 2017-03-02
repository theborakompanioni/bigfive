package org.tbk.bigfive.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface BigFiveItemRepository extends JpaRepository<BigFiveItem, Long> {

    Page<BigFiveItem> findByName(@Param("name") String name, Pageable pageable);

    Page<BigFiveItem> findByOwner(User owner, Pageable pageable);

    Page<BigFiveItem> findByList(BigFiveList list, Pageable pageable);
}
