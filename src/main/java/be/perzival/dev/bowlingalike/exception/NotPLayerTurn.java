package be.perzival.dev.bowlingalike.exception;

public class NotPLayerTurn extends Exception {

    public NotPLayerTurn() {
        super("This is not player's turn");
    }
}
