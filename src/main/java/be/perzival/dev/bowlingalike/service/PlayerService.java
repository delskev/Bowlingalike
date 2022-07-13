package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.FrameStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    Logger logger = LoggerFactory.getLogger(PlayerService.class);
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

    public int getFinalScore() {
        return frameService.getFrameList().getLast().getScore();
    }
}
