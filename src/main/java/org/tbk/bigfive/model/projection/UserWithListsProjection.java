package org.tbk.bigfive.model.projection;

import org.springframework.data.rest.core.config.Projection;
import org.tbk.bigfive.model.User;

import java.util.List;

@Projection(name = "withLists", types = {User.class})
public interface UserWithListsProjection {
    String getName();

    boolean isEnabled();

    List<ListWithItemsProjection> getLists();
}
