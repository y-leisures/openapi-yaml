package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TeamResponse", description = "The response of a team")
public record TeamResponse(
        @Schema(required = true)
        Integer id,
        @Schema(required = true)
        String name,

        @JsonProperty("url_path")
        @Schema(maxLength = 32)
        String urlPath,

        @Schema(required = true, example = "1.5", format = "float")
        Double regulationAtBats,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,

        @JsonProperty(defaultValue = "null")
        @Nullable
        LocalDateTime deletedAt
) {
}
