package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.time.LocalDateTime;

public record StadiumResponse(
        Integer id,
        String name,
        @Nullable
        @JsonProperty("map_url")
        URL mapUrl,
        @Nullable
        @JsonProperty("homepage_url")
        URL homepageUrl,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime updatedAt,
        @Nullable
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime deletedAt
) {

}