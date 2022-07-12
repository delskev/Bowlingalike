package be.perzival.dev.bowlingalike.model;

public class Throw {
    private final Integer numberOfPinDown;

    public Throw(Integer numberOfPinDown) {
        this.numberOfPinDown = numberOfPinDown;
    }

    public Integer getNumberOfPinDown() {
        return numberOfPinDown;
    }
}
