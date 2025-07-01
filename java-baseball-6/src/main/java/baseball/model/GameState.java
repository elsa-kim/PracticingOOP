package baseball.model;

import java.util.Arrays;

public enum GameState {

    CONTINUE("1"),
    EXIT("2");

    private final String code;

    GameState(String code) {
        this.code = code;
    }

    public static GameState checkState(String input) {
        return Arrays.stream(GameState.values())
                .filter(state -> state.code.equals(input)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
