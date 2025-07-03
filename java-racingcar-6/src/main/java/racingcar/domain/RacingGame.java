package racingcar.domain;

public class RacingGame {
    private final int turnNumber;

    RacingGame(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public static RacingGame fromInput(String input) {
        return new RacingGame(validate(input));
    }

    public RacingGame nextTurn() {
        return new RacingGame(turnNumber - 1);
    }

    private static int validate(String input) {
        int inputNumber = validateNumberFormat(input);
        validateIsPositiveNumber(inputNumber);
        return inputNumber;
    }

    private static int validateNumberFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("횟수는 숫자로 입력해야합니다.");
        }
    }

    private static void validateIsPositiveNumber(int turnNumber) {
        if (turnNumber <= 0) {
            throw new IllegalArgumentException("횟수는 1 이상의 수를 입력해야합니다.");
        }
    }

    public boolean isFinish() {
        return this.turnNumber == 0;
    }
}
