package org.bms.core;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bms.model.CommandSuccessResult;
import org.bms.model.TeamRequest;
import org.bms.model.TeamResponse;
import org.eclipse.microprofile.openapi.annotations.enums.Explode;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.Separator;

import java.util.Set;

// import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;
@Produces(MediaType.APPLICATION_JSON)
public interface CoreEndpointResourceIf {

    // a series of player resources
    @GET
    @Path("/players")
    Response getPlayers(
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @QueryParam("offset") int offset,
            @QueryParam("limit")
            @Parameter(schema = @Schema(type = SchemaType.NUMBER))
            @DefaultValue("50") int limit,
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "filter with team ids",
                    style = ParameterStyle.FORM, explode = Explode.FALSE,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Integer.class)
            )
            @Separator(",")
            @QueryParam("team_ids") Set<Integer> teamIds
    );

    @GET
    @Path("/players/{id}")
    Response getPlayerById(@PathParam("id") Integer playerId);

    @POST
    @Path("/players")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response createPlayer() {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @PUT
    @Path("/players/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response updatePlayer(@PathParam("id") String playerId) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @DELETE
    @Path("/players/{id}")
    default Response deletePlayer(@PathParam("id") String playerId) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    // a series of team resources
    @GET
    @Path("/teams")
    @APIResponses({
            @APIResponse(
                    responseCode = "200", description = "A list of teams",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = TeamResponse.class))
            ),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    Response getTeams();

    @GET
    @Path("/teams/{id}")
    @APIResponses({
            @APIResponse(
                    responseCode = "200", description = "The detail of a team",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = TeamResponse.class))
            ),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    Response getTeamById(@PathParam("id") Integer teamId);

    @POST
    @Path("/teams")
    @Consumes(MediaType.APPLICATION_JSON)
    default Response createTeam(@Valid TeamRequest teamRequest) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @PUT
    @Path("/teams/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(
                    responseCode = "200", description = "The result of updating a team"
                    // content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = TeamResponse.class))
            ),
            @APIResponse(responseCode = "404", description = "The team does not exist"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    default Response updateTeam(@PathParam("id") String teamId, @Valid TeamRequest teamRequest) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

    @DELETE
    @Path("/teams/{id}")
    @APIResponses({
            @APIResponse(
                    responseCode = "200", description = "The result of deleting a team",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = CommandSuccessResult.class))
            ),
            @APIResponse(responseCode = "404", description = "The team does not exist"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    default Response deleteTeam(@PathParam("id") String teamId) {
        return Response.ok(new CommandSuccessResult("ok")).build();
    }

}
