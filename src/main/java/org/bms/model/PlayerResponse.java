package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime updatedAt,
        @Nullable
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime deletedAt
) {

}