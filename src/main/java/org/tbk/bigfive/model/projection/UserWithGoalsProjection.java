package org.tbk.bigfive.model.projection;

import org.springframework.data.rest.core.config.Projection;
import org.tbk.bigfive.model.Goal;
import org.tbk.bigfive.model.User;

import java.util.List;

@Projection(name = "withGoals", types = {User.class})
public interface UserWithGoalsProjection {
    String getName();

    boolean isEnabled();

    List<Goal> getGoals();
}
