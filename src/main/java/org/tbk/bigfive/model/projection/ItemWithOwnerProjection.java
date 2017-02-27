package org.tbk.bigfive.model.projection;

import org.springframework.data.rest.core.config.Projection;
import org.tbk.bigfive.model.BigFiveItem;
import org.tbk.bigfive.model.User;

@Projection(name = "withOwner", types = {BigFiveItem.class})
public interface ItemWithOwnerProjection {
    String getName();

    String getDescription();

    User getOwner();
}
