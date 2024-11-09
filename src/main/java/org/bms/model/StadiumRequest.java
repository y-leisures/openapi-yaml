package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.net.URL;

public record StadiumRequest(
        @Null(groups = ValidationGroups.Post.class)
        Integer id,
        @Schema(description = "The name of the stadium", maxLength = 64)
        String name,
        @Nullable
        @JsonProperty("map_url")
        URL mapUrl,
        @Nullable
        @JsonProperty("homepage_url")
        URL homepageUrl
) {

}