package racingcar.model;

public class Racing {
    private final int turnNumber;

    Racing(int turnNumber) {
        validate(turnNumber);
        this.turnNumber = turnNumber;
    }

    public static Racing fromInput(String input) {
        return new Racing(validateNumberFormat(input));
    }

    private static int validateNumberFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("횟수는 숫자로 입력해야합니다.");
        }
    }

    private void validate(int turnNumber) {
        if (turnNumber <= 0) {
            throw new IllegalArgumentException("횟수는 1 이상의 수를 입력해야합니다.");
        }
    }
}
