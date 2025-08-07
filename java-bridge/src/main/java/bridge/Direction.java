package bridge;

import java.util.Arrays;

public enum Direction {
    UP("U"),
    DOWN("D");

    private final String inputKey;

    private Direction(String inputKey) {
        this.inputKey = inputKey;
    }

    public static Direction from(String input){
        return Arrays.stream(Direction.values())
                .filter(x -> x.inputKey.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
    }

}
