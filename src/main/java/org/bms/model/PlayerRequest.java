package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;

public record PlayerRequest(
        @Null(groups = ValidationGroups.Post.class)
        Integer id,
        String name,
        // @Null(groups = ValidationGroups.Post.class)
        @JsonProperty("team_id")
        Integer teamId,
        @JsonProperty("back_number")
        String backNumber
) {

}