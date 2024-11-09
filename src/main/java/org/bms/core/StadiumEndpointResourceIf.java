package org.bms.core;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bms.model.CommandSuccessResult;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Produces(MediaType.APPLICATION_JSON)
public interface StadiumEndpointResourceIf {

    @GET
    @Path("/stadiums")
    Response getStadiums(
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @PositiveOrZero
            @QueryParam("offset") int offset,
            @QueryParam("limit")
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @PositiveOrZero
            @DefaultValue("50") int limit
    );

    @GET
    @Path("/stadiums/{id}")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Stadium found"),
                    @APIResponse(responseCode = "404", description = "Stadium not found")
            }
    )
    Response getStadiumById(@PositiveOrZero @PathParam("id") Integer stadiumId);

    @POST
    @Path("/stadiums")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response createStadium() {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @PUT
    @Path("/stadiums/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response updateStadium(@PositiveOrZero @PathParam("id") String stadiumId) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @DELETE
    @Path("/stadiums/{id}")
    default Response deleteStadium(@PositiveOrZero @PathParam("id") String stadiumId) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }
}
