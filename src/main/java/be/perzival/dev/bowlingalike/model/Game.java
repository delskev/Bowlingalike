package be.perzival.dev.bowlingalike.model;

import java.util.LinkedList;

public class Game {
    private GameStatus gameStatus;
    private LinkedList<Frame> frameList;

    public Game() {
        this.gameStatus = GameStatus.CREATED;

    }

    public void startGame() {
        this.gameStatus = GameStatus.STARTED;
    }

    public void stopGame() {
        this.gameStatus = GameStatus.STOPPED;
    }

    public void endGame() {
        this.gameStatus = GameStatus.ENDED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public LinkedList<Frame> getFrameList() {
        return frameList;
    }
}
