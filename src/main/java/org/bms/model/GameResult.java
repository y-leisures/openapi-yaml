package org.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.PositiveOrZero;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "GameResult", description = "The result of a baseball game")
public record GameResult(
        @Schema(description = "The unique identifier of the game")
        Integer id,
        @JsonProperty("start_time")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime startTime,
        @JsonProperty("end_time")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime endTime,

        @JsonProperty("game_status")
        GameStatus status,

        @JsonProperty("stadium_id")
        Integer stadiumId,
        Score score,
        String topTeam,
        String bottomTeam
) {
    public record Score(
            @PositiveOrZero
            Integer topTeamScore,
            @PositiveOrZero
            Integer bottomTeamScore
    ) {
        @Override
        public String toString() {
            return topTeamScore + " - " + bottomTeamScore;
        }
    }

    public enum GameStatus {
        PENDING("試合開始前", "試合がまだ始まっていません。"),
        IN_PROGRESS("試合中", "試合が進行中です。"),
        FINISHED("試合終了", "試合が終了しました。"),
        POSTPONED("試合延期", "試合が延期されました。"),
        CANCELLED("試合中止", "試合が中止されました。");

        private final String displayName;
        private final String description;

        GameStatus(String displayName, String description) {
            this.displayName = displayName;
            this.description = description;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getDescription() {
            return description;
        }

        public GameStatus nextStatus() {
            return switch (this) {
                case PENDING -> IN_PROGRESS;
                case IN_PROGRESS -> FINISHED;
                case FINISHED -> POSTPONED;
                default -> this;
            };
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
