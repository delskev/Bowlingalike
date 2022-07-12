package be.perzival.dev.bowlingalike.exception;

public class TooManyThrowsException extends Exception {

    public TooManyThrowsException() {
        super("There's too many throw for this frame");
    }
}
