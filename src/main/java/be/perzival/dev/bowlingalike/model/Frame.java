package be.perzival.dev.bowlingalike.model;


import java.util.LinkedList;
import java.util.List;

public class Frame {
    public static final int MAX_PINS = 15;
    private final int id;
    private LinkedList<Integer> throwList;
    private Integer numberOfPinsLeft;
    private final boolean lastFrame;
    private boolean bonusFrame;
    private FrameStatus frameStatus;
    private int score;
    private int throwsLeft;

    public Frame(int id, boolean lastFrame) {
        this.id = id;
        this.lastFrame = lastFrame;
        this.bonusFrame = false;
        this.numberOfPinsLeft = MAX_PINS;
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
            this.frameStatus = numberOfPinDown == MAX_PINS ? FrameStatus.STRIKE : FrameStatus.PINS_LEFT;
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
        if( !bonusFrame && lastFrame && (frameStatus.equals(FrameStatus.SPARE) || frameStatus.equals(FrameStatus.STRIKE))) {
            numberOfPinsLeft = MAX_PINS;
            this.throwsLeft = frameStatus.equals(FrameStatus.SPARE) ? 2 : 3;//bonus throw
            this.frameStatus = FrameStatus.FULL;
            this.bonusFrame = true;
        }
        handleBonus();
    }

    private void handleBonus() {
        if(bonusFrame) {
            if( numberOfPinsLeft == 0) {
                numberOfPinsLeft = MAX_PINS;
            }
            this.frameStatus = throwsLeft > 0 ? FrameStatus.PINS_LEFT : FrameStatus.NO_MORE_SHOT;
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

    public int getScore() {
        return score;
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
