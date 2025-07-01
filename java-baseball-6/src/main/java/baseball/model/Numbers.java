package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Numbers {
    private static final int NUMBER_LENGTH = 3;

    private final List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Numbers fromString(String inputNumber) {
        validateNumberFormat(inputNumber);
        return new Numbers(Arrays.stream(inputNumber.split(""))
                .map(Integer::parseInt)
                .toList());

    }

    public static Numbers generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 3) {
            int random = Randoms.pickNumberInRange(1, 9);
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

    private static void validateNumberFormat(String inputNumber) {
        try {
            Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> inputNumber) {
        if (!isInRange(inputNumber)) {
            throw new IllegalArgumentException("1 이상 9 이하의 숫자만 가능합니다.");
        }
    }

    private boolean isInRange(List<Integer> inputNumber) {
        return inputNumber.stream().allMatch(num -> num >= 1 && num <= 9);
    }

    private void validateLength(List<Integer> inputNumber) {
        if (inputNumber.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException(String.format("%d자리 숫자를 입력해야 합니다.", NUMBER_LENGTH));
        }
    }

    private void validateNoDuplicate(List<Integer> inputNumber) {
        if (hasDuplicate(inputNumber)) {
            throw new IllegalArgumentException("중복값이 존재하면 안됩니다.");
        }
    }

    private boolean hasDuplicate(List<Integer> inputNumber) {
        return inputNumber.size() != inputNumber.stream()
                .distinct()
                .count();
    }

    public Result evaluateMatches(Numbers userNumber) {
        int ball = 0;
        int strike = 0;

        for (int i = 0; i < numbers.size(); i++) {
            if (userNumber.isStrike(numbers.get(i), i)) {
                strike++;
            } else if (userNumber.isBall(numbers.get(i))) {
                ball++;
            }
        }
        return new Result(ball, strike);
    }

    private boolean isBall(Integer targetNumber) {
        return numbers.contains(targetNumber);
    }

    private boolean isStrike(Integer targetNumber, int idx) {
        return numbers.get(idx).equals(targetNumber);
    }
}
