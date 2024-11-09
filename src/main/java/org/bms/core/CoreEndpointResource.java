package org.bms.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.bms.annotation.BmsBasic;

import java.util.Set;

@ApplicationScoped
@Path("/api/v1/core")
@BmsBasic
public class CoreEndpointResource implements TeamAndPlayerEndpointResourceIf {
    @Override
    public Response getPlayers(int offset, int limit, Set<Integer> teamIds) {
        return null;
    }

    @Override
    public Response getPlayerById(Integer playerId) {
        return null;
    }

    @Override
    public Response getTeams() {
        return null;
    }

    @Override
    public Response getTeamById(Integer teamId) {
        return null;
    }
}
