package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class FrameService {
    Logger logger = LoggerFactory.getLogger(FrameService.class);
    private LinkedList<Frame> frameList;

    public FrameService() {
        this.frameList = new LinkedList<>();
        this.frameList.add(new Frame());
    }


    public void addThrow(Throw currentThrow) {
        Frame currentFrame = getCurrentFrame();
        currentFrame.updateFrameStatus(currentThrow.getNumberOfPinDown());
        logger.info("Is there any pin left for throwing: {}", currentFrame.getNumberOfPinsLeft());
        if( currentThrow.getNumberOfPinDown() <= currentFrame.getNumberOfPinsLeft()) {
            logger.info("player throw wood and down {} pin(s)", currentThrow.getNumberOfPinDown());
            currentFrame.getThrowList().add(currentThrow);
            currentFrame.updatePinsLeftAndScore(currentThrow);
        }
    }

    public Frame getCurrentFrame() {
        logger.info("Getting current frame");
        Frame currentFrame = this.frameList.getLast();

        logger.info("Actual frame is {} with {} shots", this.frameList.size(), currentFrame.getThrowList().size());
        if( currentFrame.getThrowList().size() < 3 ) { //TODO: handle last frame
            return currentFrame;
        }
        currentFrame = new Frame(currentFrame,this.frameList.size() == 4);
        logger.info("Adding frame to the game");
        this.frameList.add(currentFrame);

        return currentFrame;
    }

    public LinkedList<Frame> getFrameList() {
        return frameList;
    }
}
