package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        @Nullable
        LocalDateTime deletedAt
) {

}