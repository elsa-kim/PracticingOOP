package racingcar.domain;

import java.util.Objects;

public class CarName {
    private static final String ERROR_CAR_NAME_NOT_BLANK = "자동차 이름에 문자를 포함해야합니다.";
    private static final String ERROR_CAR_NAME_LENGTH_OUT_OF_RANGE = "자동차 이름은 %d글자 이상 %d글자 이하로 작성해야 합니다.";
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 1;

    private final String name;

    private CarName(String name) {
        validate(name);
        this.name = name;
    }

    public static CarName from(String input) {
        return new CarName(input);
    }

    public String getName() {
        return this.name;
    }

    private void validate(String name) {
        validateLength(name);
        validateNotBlank(name);
    }

    private void validateNotBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_CAR_NAME_NOT_BLANK);
        }
    }

    private void validateLength(String name) {
        if (isValidLength(name)) {
            throw new IllegalArgumentException(ERROR_CAR_NAME_LENGTH_OUT_OF_RANGE
                    .formatted(MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
    }

    private boolean isValidLength(String name) {
        int length = name.length();
        return length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarName carName = (CarName) o;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
