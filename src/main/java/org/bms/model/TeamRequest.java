package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record TeamRequest(

        @Null(groups = ValidationGroups.Post.class)
        Integer id,
        String name,

        @JsonProperty("url_path")
        @Size(min = 4, max = 32)
        String urlPath,

        @JsonProperty(defaultValue = "2.0")
        @Schema(minimum = "0", maximum = "5")
        @Positive
        Double regulationAtBats
) {

}
