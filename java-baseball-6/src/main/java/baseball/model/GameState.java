package baseball.model;

public enum GameState {

    CONTINUE("1"),
    EXIT("2");

    private final String code;

    GameState(String code) {
        this.code = code;
    }
}
