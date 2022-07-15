package be.perzival.dev.bowlingalike.mapper;

import be.perzival.dev.bowlingalike.dto.ImmutableScoreBoard;
import be.perzival.dev.bowlingalike.dto.ScoreBoardDTO;
import be.perzival.dev.bowlingalike.model.Frame;

import java.util.List;

public interface ScoreBoardDTOMapper {


    static ScoreBoardDTO toJson(List<Frame> frame) {
        ImmutableScoreBoard.Builder builder = ImmutableScoreBoard.builder();

        for (Frame currentFrame : frame) {
            builder.addScoreList(ScoreDTOMapper.toJson(currentFrame));
        }
        return builder.build();
    }
}
