package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.FrameStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FrameService {
    public static final int MAX_FRAME = 5;
    Logger logger = LoggerFactory.getLogger(FrameService.class);

    private LinkedList<Frame> frameList;

    public FrameService() {
        this.frameList = new LinkedList<>();
        this.frameList.add(new Frame());
    }


    public Frame addThrow(int currentThrow) {
        Frame currentFrame = getCurrentFrame();
        currentFrame.updatePinsLeftAndScore(currentThrow);
        currentFrame.updateFrameStatus(currentThrow);

        return currentFrame;
    }

    public Frame getCurrentFrame() {
        logger.info("Getting current frame");
        Frame currentFrame = this.frameList.getLast();

        logger.info("Actual frame is {} with {} shot(s) in status {}", this.frameList.size(), currentFrame.getThrowList().size(), currentFrame.getFrameStatus());
        if( FrameStatus.isNotStrikeOrSpareOrEnded(currentFrame.getFrameStatus()) ) {
            return currentFrame;
        }
        currentFrame = new Frame(frameList.size(),this.frameList.size() == MAX_FRAME - 1);
        logger.info("Adding frame to the game");
        this.frameList.add(currentFrame);

        return currentFrame;
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public void processScore() {
        for (Frame currentFrame : frameList) {
            if( !FrameStatus.ENDED.equals(currentFrame.getFrameStatus()) && currentFrame.getId() > 0) {
                currentFrame.updateScore(frameList.get(currentFrame.getId() - 1).getScore());
            }
            if(FrameStatus.STRIKE.equals(currentFrame.getFrameStatus()) ) {
                processBonus(currentFrame, 3);
            }
            if(FrameStatus.SPARE.equals(currentFrame.getFrameStatus()) ) {
                processBonus(currentFrame, 2);
            }
            currentFrame.setFrameStatus(FrameStatus.ENDED);
        }
    }

    private void processBonus(Frame currentFrame, int bonus) {
        for (int i = currentFrame.getId() + 1; i < frameList.size() && bonus > 0; i++) {
            List<Integer> scoreToProcess = frameList.get(i).getThrowList();
            for (int j = 0; j < scoreToProcess.size() && bonus > 0; j++, bonus--) {
                currentFrame.updateScore(scoreToProcess.get(j));
            }
        }
    }
}
