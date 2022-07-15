package be.perzival.dev.bowlingalike.mapper;

import be.perzival.dev.bowlingalike.dto.ImmutableScoreDTO;
import be.perzival.dev.bowlingalike.dto.ScoreDTO;
import be.perzival.dev.bowlingalike.model.Frame;

import java.util.List;

public interface ScoreDTOMapper {


    static ScoreDTO toJson(Frame frame) {
        ImmutableScoreDTO.Builder builder = ImmutableScoreDTO.builder()
                .frame(frame.getId() + 1)
                .score(frame.getScore());

        int score = 0;
        List<Integer> throwList = frame.getThrowList();
        for (int i = 0; i < frame.getThrowList().size(); i++) {
            score += throwList.get(i);
            handleSpareOrStrike(builder, score, throwList, i);
        }
        return builder.build();
    }

    private static void handleSpareOrStrike(ImmutableScoreDTO.Builder builder, int score, List<Integer> throwList, int i) {
        switch (i) {
            case 0:
                builder.shoot1(score == 15 ? "X" : throwList.get(i).toString());
                break;
            case 1:
                builder.shoot2(score == 15 ? "/" : throwList.get(i).toString());
                break;
            case 2:
                builder.shoot3(score == 15 ? "/" : throwList.get(i).toString());
                break;
            case 3:
                builder.shoot4(score == 15 ? "/" : throwList.get(i).toString());
                break;
            case 4:
                builder.shoot5(score == 15 ? "/" : throwList.get(i).toString());
                break;
            default:
                //nothing
                break;
        }
    }
}
