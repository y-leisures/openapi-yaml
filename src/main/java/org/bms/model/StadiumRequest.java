package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;

import java.net.URL;

public record StadiumRequest(
        @Null(groups = ValidationGroups.Post.class)
        Integer id,
        String name,
        @Nullable
        @JsonProperty("map_url")
        URL mapUrl,
        @Nullable
        @JsonProperty("homepage_url")
        URL homepageUrl
) {

}