package racingcar.domain;

public class RaceCount {
    private static final String ERROR_NOT_NUMERIC = "숫자를 입력해야합니다.";
    private static final String ERROR_OUT_OF_RANGE = "1 이상의 수를 입력해야합니다.";

    private final int count;

    RaceCount(int count) {
        validateIsPositiveNumber(count);
        this.count = count;
    }

    public static RaceCount fromInput(String input) {
        try {
            int turn = Integer.parseInt(input);
            return new RaceCount(turn);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMERIC);
        }
    }

    private void validateIsPositiveNumber(int turnNumber) {
        if (turnNumber < 0) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public boolean isFinish(int tryCnt) {
        return this.count == tryCnt;
    }
}
