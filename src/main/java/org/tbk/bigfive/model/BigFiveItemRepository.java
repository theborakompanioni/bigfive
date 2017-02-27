package org.tbk.bigfive.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BigFiveItemRepository extends JpaRepository<BigFiveItem, Long> {

    List<BigFiveItem> findByName(String name);

    List<BigFiveItem> findByList(BigFiveList list);
}
