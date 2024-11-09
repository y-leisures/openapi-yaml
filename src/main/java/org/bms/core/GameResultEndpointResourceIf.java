package org.bms.core;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Produces(MediaType.APPLICATION_JSON)
public interface GameResultEndpointResourceIf {
    @GET
    @Path("/games/{id}")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Game is found"),
            @APIResponse(responseCode = "404", description = "Game is not found")
    })
    Response getGameResultById(@PositiveOrZero @PathParam("id") Integer gameId);

    @GET
    @Path("/games/")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Game is found"),
            @APIResponse(responseCode = "404", description = "Game is not found")
    })
    Response getGameResults(
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @PositiveOrZero
            @QueryParam("offset") int offset,
            @QueryParam("limit")
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @PositiveOrZero
            @DefaultValue("50") int limit
    );

}
