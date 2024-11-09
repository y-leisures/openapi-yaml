package org.bms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
        @JsonProperty("regulation_at_bats")
        @Min(0)
        @Max(5)
        @JsonSerialize(using = AtBatSerializer.class)
        Double regulationAtBats,

        @JsonProperty("created_at")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime updatedAt,

        @JsonProperty("deleted_at")
        @Nullable
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime deletedAt
) {
}
