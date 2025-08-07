package bridge;

import java.util.Arrays;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private final String inputKey;

    private GameCommand(String inputKey) {
        this.inputKey = inputKey;
    }

    public static GameCommand from(String input){
        return Arrays.stream(GameCommand.values())
                .filter(x -> x.inputKey.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
