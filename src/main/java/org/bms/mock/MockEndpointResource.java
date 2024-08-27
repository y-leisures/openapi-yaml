package org.bms.mock;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.bms.core.CoreEndpointResourceIf;
import org.bms.model.PlayerResponse;
import org.bms.model.TeamResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

import static org.bms.BmsConstants.MOCK_BMS_BASIC_API;
import static org.bms.BmsConstants.MOCK_BMS_COMMON_APIS_FOR_AUTHENTICATED_USERS;

@ApplicationScoped
@Path("/dummy/api/v1/core")
@Tag(name = MOCK_BMS_BASIC_API, description = MOCK_BMS_COMMON_APIS_FOR_AUTHENTICATED_USERS)
public class MockEndpointResource implements CoreEndpointResourceIf {
    @Override
    public Response getPlayers(int offset, int limit, Set<Integer> teamIds) {
        List<PlayerResponse> players = MockDataGenerator.generatePlayers(teamIds, limit);
        return Response.ok(players).build();
    }

    @Override
    public Response getPlayerById(Integer playerId) {
        PlayerResponse player = MockDataGenerator.generatePlayer(playerId);
        return Response.ok(player).build();
    }

    @Override
    public Response getTeams() {
        List<TeamResponse> teams = MockDataGenerator.generateTeams(20);
        return Response.ok(teams).build();
    }

    @Override
    public Response getTeamById(Integer teamId) {
        TeamResponse team = MockDataGenerator.generateTeam(teamId);
        return Response.ok(team).build();
    }
}
