package be.perzival.dev.bowlingalike.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class Frame {
    Logger logger = LoggerFactory.getLogger(Frame.class);
    private final int id;
    private LinkedList<Integer> throwList;
    private Integer numberOfPinsLeft;
    private final boolean isLastFrame;
    private FrameStatus frameStatus;
    private int score;

    private int throwsLeft;

    public Frame(int id, boolean isLastFrame) {
        this.id = id;
        this.isLastFrame = isLastFrame;
        this.numberOfPinsLeft = 15;
        this.throwList = new LinkedList<>();
        this.score = 0;
        this.throwsLeft = 3;
        this.frameStatus = FrameStatus.FULL;
    }
    public Frame() {
        this(0,false);
    }

    public void updateFrameStatus(int numberOfPinDown) {
        if(FrameStatus.FULL.equals(frameStatus)) {
            this.frameStatus = numberOfPinDown == 15 ? FrameStatus.STRIKE : FrameStatus.PINS_LEFT;
        }else if(FrameStatus.PINS_LEFT.equals(frameStatus)) {
            if( numberOfPinsLeft == 0) {
                frameStatus = FrameStatus.SPARE;
            } else if ( throwsLeft == 0) {
                frameStatus = FrameStatus.NO_MORE_SHOT;
            }
        }
        handleLastFrame();
    }

    private void handleLastFrame() {
        if(isLastFrame && (frameStatus.equals(FrameStatus.SPARE) || frameStatus.equals(FrameStatus.STRIKE))) {
            numberOfPinsLeft = 15;
            this.throwsLeft = frameStatus.equals(FrameStatus.SPARE) ? 2 : 3;
            this.frameStatus = FrameStatus.FULL;
        }
    }

    public void setFrameStatus(FrameStatus frameStatus) {
        this.frameStatus = frameStatus;
    }

    public void updatePinsLeftAndScore(int currentThrow) {
        this.throwList.add(currentThrow);
        numberOfPinsLeft -= currentThrow;
        throwsLeft--;
        updateScore(currentThrow);
    }

    public void updateScore(int numberOfPoints) {
        score += numberOfPoints;
    }

    public List<Integer> getThrowList() {
        return throwList;
    }

    public int getId() {
        return id;
    }

    public Integer getNumberOfPinsLeft() {
        return numberOfPinsLeft;
    }

    public int getScore() {
        return score;
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
