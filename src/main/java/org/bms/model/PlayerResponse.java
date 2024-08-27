package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public record PlayerResponse(
        Integer id,
        String name,
        // @Null(groups = ValidationGroups.Post.class)
        @JsonProperty("team_id")
        Integer teamId,
        @JsonProperty("back_number")
        String backNumber,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        @Nullable
        LocalDateTime deletedAt
) {

}