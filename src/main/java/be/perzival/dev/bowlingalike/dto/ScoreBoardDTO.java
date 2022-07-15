package be.perzival.dev.bowlingalike.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@ImmutableWithStaticConstructor
@JsonSerialize(as = ImmutableScoreBoard.class)
@JsonDeserialize(as = ImmutableScoreBoard.class)
public interface ScoreBoardDTO {

    List<ScoreDTO> getScoreList();
}
