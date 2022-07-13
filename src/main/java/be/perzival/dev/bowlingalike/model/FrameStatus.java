package be.perzival.dev.bowlingalike.model;


public enum FrameStatus {
    FULL,
    PINS_LEFT,
    SPARE,
    STRIKE,
    ENDED,
    NO_MORE_SHOT;


    public static boolean isStrikeOrSpareOrEnded(FrameStatus frameStatus) {
        return STRIKE.equals(frameStatus) || SPARE.equals(frameStatus) || ENDED.equals(frameStatus);
    }
    public static boolean isNotStrikeOrSpareOrEnded(FrameStatus frameStatus) {
        return !isStrikeOrSpareOrEnded(frameStatus);
    }
}
