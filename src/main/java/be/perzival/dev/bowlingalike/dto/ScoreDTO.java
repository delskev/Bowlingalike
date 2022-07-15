package be.perzival.dev.bowlingalike.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@ImmutableWithStaticConstructor
@JsonSerialize(as = ImmutableScoreDTO.class)
@JsonDeserialize(as = ImmutableScoreDTO.class)
public interface ScoreDTO {
    int getFrame();
    @Nullable
    String getShoot1();
    @Nullable
    String getShoot2();
    @Nullable
    String getShoot3();
    @Nullable
    String getShoot4();
    @Nullable
    String getShoot5();
    int getScore();
}
