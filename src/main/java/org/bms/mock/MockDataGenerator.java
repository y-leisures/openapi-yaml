package org.bms.mock;

import org.bms.model.PlayerResponse;
import org.bms.model.TeamResponse;
import org.instancio.Assign;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.instancio.Select.field;

public class MockDataGenerator {

    static List<TeamResponse> generateTeams(Integer sizeOfTeams) {
        var predicate = Assign
                .valueOf(field(TeamResponse::createdAt))
                .to(field(TeamResponse::updatedAt))
                .as((LocalDateTime c) -> c.plus(Duration.ofDays(730)));
        List<TeamResponse> teamResponses = Instancio.ofList(TeamResponse.class)
                .assign(predicate)
                .ignore(field(TeamResponse::deletedAt))
                .generate(field(TeamResponse::createdAt), gen -> gen.temporal().localDateTime().range(LocalDateTime.now().minusYears(10), LocalDateTime.now().minusDays(10)))
                .generate(field(TeamResponse::urlPath), gen -> gen.string().lowerCase().length(4, 16))
                .create();
        return teamResponses;
    }

    static TeamResponse generateTeam(Integer teamId) {
        var predicate = Assign
                .valueOf(field(TeamResponse::createdAt))
                .to(field(TeamResponse::updatedAt))
                .as((LocalDateTime c) -> c.plus(Duration.ofDays(365)));
        return Instancio.of(TeamResponse.class)
                .assign(predicate)
                .set(field(TeamResponse::id), teamId)
                .set(field(TeamResponse::deletedAt), null)
                .generate(field(TeamResponse::urlPath), gen -> gen.string().lowerCase().length(4, 16))
                .generate(field(TeamResponse::createdAt), gen -> gen.temporal().localDateTime().range(LocalDateTime.now().minusYears(10), LocalDateTime.now().minusDays(10)))
                .create();
    }

    /*
        Method for players object
    */

    private static Model<PlayerResponse> providePlayerModel(Integer teamId) {
        return Instancio.of(PlayerResponse.class)
                // .ignore(field(PlayerResponse::id))
                .set(field(PlayerResponse::teamId), teamId)
                .generate(field(PlayerResponse::createdAt), gen -> gen.temporal().localDateTime().past())
                .generate(field(PlayerResponse::backNumber), gen -> gen.string().numericSequence())
                .toModel();
    }

    static PlayerResponse generatePlayer(Integer playerId) {
        return Instancio.of(PlayerResponse.class)
                .generate(field(PlayerResponse::teamId), gen -> gen.ints().range(1, 127))
                .set(field(PlayerResponse::id), playerId)
                .generate(field(PlayerResponse::backNumber), gen -> gen.ints().range(1, 127).as(i -> String.format("%2d", i)))
                .ignore(field(PlayerResponse::deletedAt))
                .create();
    }

    static List<PlayerResponse> generatePlayers(Integer teamId, Integer sizeOfPlayers) {
        return Instancio.ofList(PlayerResponse.class)
                .size(sizeOfPlayers)
                .set(field(PlayerResponse::teamId), teamId)
                .create();
    }

    static List<PlayerResponse> generatePlayers(Set<Integer> teamIds, Integer sizeOfPlayers) {
        if (teamIds.isEmpty()) {
            return Instancio.ofList(PlayerResponse.class)
                    .size(sizeOfPlayers)
                    .generate(field(PlayerResponse::teamId), gen -> gen.oneOf(teamIds))
                    .create();
        } else {
            Integer first = teamIds.stream().toList().get(0);
            Model<PlayerResponse> model = providePlayerModel(first);
            return IntStream.rangeClosed(1, 20)
                    .mapToObj(i -> Instancio.of(model)
                            .set(field(PlayerResponse::id), i)
                            .create())
                    .collect(Collectors.toList());
        }
    }
}
