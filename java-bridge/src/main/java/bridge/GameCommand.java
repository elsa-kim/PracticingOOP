package bridge;

import java.util.Arrays;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private static final String ERROR_INVALID_INPUT = "잘못된 입력입니다.";
    private final String inputKey;

    private GameCommand(String inputKey) {
        this.inputKey = inputKey;
    }

    public static GameCommand from(String input){
        return Arrays.stream(GameCommand.values())
                .filter(x -> x.inputKey.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException(ERROR_INVALID_INPUT));
    }
}
