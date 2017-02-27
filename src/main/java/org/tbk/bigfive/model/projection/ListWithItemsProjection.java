package org.tbk.bigfive.model.projection;

import org.springframework.data.rest.core.config.Projection;
import org.tbk.bigfive.model.BigFiveItem;
import org.tbk.bigfive.model.BigFiveList;

import java.util.List;

@Projection(name = "withItems", types = {BigFiveList.class})
public interface ListWithItemsProjection {
    String getName();

    String getDescription();

    List<BigFiveItem> getItems();
}
