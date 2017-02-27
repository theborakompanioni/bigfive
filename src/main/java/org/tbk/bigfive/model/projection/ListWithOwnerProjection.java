package org.tbk.bigfive.model.projection;

import org.springframework.data.rest.core.config.Projection;
import org.tbk.bigfive.model.BigFiveList;
import org.tbk.bigfive.model.User;

@Projection(name = "withOwner", types = {BigFiveList.class})
public interface ListWithOwnerProjection {
    String getName();

    String getDescription();

    User getOwner();
}
