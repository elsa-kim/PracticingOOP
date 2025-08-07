package bridge;

import java.util.Arrays;

public enum Direction {
    UP("U", 1),
    DOWN("D", 0);

    private final String inputKey;
    private final int bridgeNumber;

    private Direction(String inputKey, int bridgeNumber) {
        this.inputKey = inputKey;
        this.bridgeNumber = bridgeNumber;
    }

    public static Direction from(String input){
        return Arrays.stream(Direction.values())
                .filter(x -> x.inputKey.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
    }

}
