package org.tbk.bigfive.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = ListWithOwnerProjection.class, collectionResourceRel = "list", path = "list")
@RepositoryRestResource(collectionResourceRel = "list", path = "list")
public interface BigFiveListRepository extends JpaRepository<BigFiveList, Long> {

    Page<BigFiveList> findByName(@Param("name") String name, Pageable pageable);

    Page<BigFiveList> findByItems(BigFiveItem item, Pageable pageable);

    Page<BigFiveList> findByOwner(User owner, Pageable pageable);

    Page<BigFiveList> findByUser(User user, Pageable pageable);
}
