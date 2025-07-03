package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Numbers {
    private static final String ERROR_OUT_OF_RANGE_MESSAGE = "%d 이상 %d 이하의 숫자만 가능합니다.";
    private static final String ERROR_INVALID_LENGTH_MESSAGE = "%d자리 숫자를 입력해야 합니다.";
    private static final String ERROR_DUPLICATE_VALUE_MESSAGE = "중복값이 존재하면 안됩니다.";
    private static final String ERROR_NOT_A_NUMBER_MESSAGE = "숫자만 입력해야 합니다.";

    private static final int NUMBER_LENGTH = 3;
    private static final int VALID_NUMBER_MIN = 1;
    private static final int VALID_NUMBER_MAX = 9;

    private final List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Numbers fromString(String inputNumber) {
        try {
            return new Numbers(Arrays.stream(inputNumber.split(""))
                    .map(Integer::parseInt)
                    .toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_A_NUMBER_MESSAGE);
        }
    }

    public static Numbers generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < NUMBER_LENGTH) {
            int random = Randoms.pickNumberInRange(VALID_NUMBER_MIN, VALID_NUMBER_MAX);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return new Numbers(numbers);
    }

    private void validate(List<Integer> inputNumber) {
        validateNumberRange(inputNumber);
        validateLength(inputNumber);
        validateNoDuplicate(inputNumber);
    }

    private void validateNumberRange(List<Integer> inputNumber) {
        if (!isInRange(inputNumber)) {
            throw new IllegalArgumentException(
                    String.format(ERROR_OUT_OF_RANGE_MESSAGE, VALID_NUMBER_MIN, VALID_NUMBER_MAX));
        }
    }

    private boolean isInRange(List<Integer> inputNumber) {
        return inputNumber.stream()
                .allMatch(num -> num >= VALID_NUMBER_MIN && num <= VALID_NUMBER_MAX);
    }

    private void validateLength(List<Integer> inputNumber) {
        if (inputNumber.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_LENGTH_MESSAGE, NUMBER_LENGTH));
        }
    }

    private void validateNoDuplicate(List<Integer> inputNumber) {
        if (hasDuplicate(inputNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_VALUE_MESSAGE);
        }
    }

    private boolean hasDuplicate(List<Integer> inputNumber) {
        return inputNumber.size() != inputNumber.stream()
                .distinct()
                .count();
    }

    public Result compareWith(Numbers other) {
        int ball = 0;
        int strike = 0;

        for (int i = 0; i < numbers.size(); i++) {
            if (other.isStrike(numbers.get(i), i)) {
                strike++;
            } else if (other.isBall(numbers.get(i))) {
                ball++;
            }
        }
        return new Result(ball, strike);
    }

    private boolean isBall(Integer targetNumber) {
        return numbers.contains(targetNumber);
    }

    private boolean isStrike(Integer targetNumber, int idx) {
        return Objects.equals(numbers.get(idx), targetNumber);
    }
}
