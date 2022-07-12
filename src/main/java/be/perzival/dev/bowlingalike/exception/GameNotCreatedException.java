package be.perzival.dev.bowlingalike.exception;

public class GameNotCreatedException extends Exception {

    public GameNotCreatedException() {
        super("The game is not created yet !");
    }
}
