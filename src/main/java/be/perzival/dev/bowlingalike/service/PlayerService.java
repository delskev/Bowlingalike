package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.dto.ScoreBoardDTO;
import be.perzival.dev.bowlingalike.mapper.ScoreBoardDTOMapper;
import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.FrameStatus;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final FrameService frameService;

    public PlayerService(FrameService frameService) {
        this.frameService = frameService;
    }

    public void playTurn(int numberOfPin) {
        Frame currentFrame = frameService.addThrow(numberOfPin);
        if( FrameStatus.NO_MORE_SHOT.equals(currentFrame.getFrameStatus())) {
            frameService.processScore();
        }
    }

    public ScoreBoardDTO getScore() {
        return ScoreBoardDTOMapper.toJson(frameService.getFrameList());
    }

    public int getFinalScore() {
        return frameService.getFrameList().get(frameService.getFrameList().size() - 1).getScore();
    }
}
