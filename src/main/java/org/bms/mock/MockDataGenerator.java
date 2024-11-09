package org.bms.mock;

import net.datafaker.Faker;
import org.bms.model.PlayerResponse;
import org.bms.model.StadiumResponse;
import org.bms.model.TeamResponse;
import org.instancio.Instancio;
import org.instancio.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.instancio.Assign.valueOf;
import static org.instancio.Select.field;

public class MockDataGenerator {

    static List<TeamResponse> generateTeams(Integer sizeOfTeams) {
        var predicate = valueOf(field(TeamResponse::createdAt))
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
        var predicate = valueOf(field(TeamResponse::createdAt))
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

    /*
        Method for Stadium object
    */
    private static Model<StadiumResponse> provideStadiumModel() {
        var address = new Faker(Locale.JAPAN).address();
        Supplier<String> generateStadiumName = () -> "%s 野球場".formatted(address.city());
        var faker = new Faker();
        Supplier<URL> generateMapUrl = () -> {
            var domainPrefix = "https://maps.app.goo.gl/";
            try {
                return new URL(domainPrefix + faker.random().hex(16));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        };
        Supplier<URL> generateHomepageUrl = () -> {
            try {
                return new URL(faker.internet().url());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        };

        // see: https://www.instancio.org/user-guide/#using-assign
        return Instancio.of(StadiumResponse.class)
                .supply(field(StadiumResponse::mapUrl), generateMapUrl)
                .supply(field(StadiumResponse::homepageUrl), generateHomepageUrl)
                .supply(field(StadiumResponse::name), generateStadiumName::get)
                .generate(field(StadiumResponse::createdAt), gen -> gen.temporal().localDateTime().past())
                .assign(valueOf(StadiumResponse::createdAt).to(field(StadiumResponse::updatedAt))
                        .as((LocalDateTime c) -> c.plus(Duration.ofDays(30)))
                )
                .generate(field(StadiumResponse::deletedAt), gen -> gen.temporal().localDateTime().future().nullable())
                .toModel();
    }

    static StadiumResponse generateStadium(Integer stadiumId) {
        return Instancio
                .of(provideStadiumModel())
                .set(field(StadiumResponse::id), stadiumId)
                .create();
    }

    static List<StadiumResponse> generateStadiums(Integer sizeOfStadiums) {
        return Instancio.ofList(provideStadiumModel())
                .size(sizeOfStadiums)
                .generate(field(StadiumResponse::id), gen -> gen.ints().range(1, 100))
                .create();
    }
}
