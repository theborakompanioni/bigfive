package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BigFiveListRepository extends JpaRepository<BigFiveList, Long> {

    List<BigFiveList> findByName(String name);

    List<BigFiveList> findByItems(BigFiveItem item);

    List<BigFiveList> findByOwner(User owner);

    List<BigFiveList> findByUser(User user);
}
