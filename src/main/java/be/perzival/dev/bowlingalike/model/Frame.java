package be.perzival.dev.bowlingalike.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class Frame {
    Logger logger = LoggerFactory.getLogger(Frame.class);
    private LinkedList<Throw> throwList;
    private Integer numberOfPinsLeft;
    private final boolean isLastFrame;
    private FrameStatus frameStatus;
    private int score;
    private Frame previousFrame;

    public Frame(Frame previousFrame, boolean isLastFrame) {
        this(previousFrame.getScore(), previousFrame, isLastFrame);
    }
    public Frame() {
        this(0, null, false);
    }
    private Frame(int score, Frame frame, boolean isLastFrame) {
        this.isLastFrame = isLastFrame;
        this.numberOfPinsLeft = 15;
        this.throwList = new LinkedList<>();
        this.score = score;
        this.previousFrame = frame;
        this.frameStatus = FrameStatus.FULL;
    }

    public void updateFrameStatus(int numberOfPinDown) {
        switch (this.frameStatus) {
            case FULL:
                this.frameStatus = numberOfPinDown == 15 ? FrameStatus.STRIKE : FrameStatus.PINS_LEFT;
                break;
            case PINS_LEFT:
                this.frameStatus = numberOfPinDown == this.numberOfPinsLeft ? FrameStatus.SPARE : FrameStatus.PINS_LEFT;
                break;
            default:
                //Nothing yet
                break;
        }
    }

    public void updatePinsLeftAndScore(Throw currentThrow) {
        numberOfPinsLeft -= currentThrow.getNumberOfPinDown();
        score += currentThrow.getNumberOfPinDown();
    }

    public List<Throw> getThrowList() {
        return throwList;
    }

    public boolean isLastThrow() {
        return !this.isLastFrame && this.throwList.size() == 3;
    }

    public Integer getNumberOfPinsLeft() {
        return numberOfPinsLeft;
    }

    public int getScore() {
        return score;
    }
}
