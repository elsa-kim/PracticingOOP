package bridge.domain;

import java.util.Arrays;

public enum Direction {
    UP("U", 1),
    DOWN("D", 0);

    private final String inputKey;
    private final int generateNumber;

    private Direction(String inputKey, int generateNumber) {
        this.inputKey = inputKey;
        this.generateNumber = generateNumber;
    }

    public static Direction from(String input){
        return Arrays.stream(Direction.values())
                .filter(x -> x.inputKey.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public static String generate(int bridgeNumber) {
        return Arrays.stream(Direction.values())
                .filter(x -> x.generateNumber == bridgeNumber)
                .map(x -> x.inputKey)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 생성값입니다."));
    }
}
