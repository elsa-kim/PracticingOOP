package menu.domain;

import java.util.Objects;

public class CoachName {
    private static final String ERROR_INVALID_COACH_NAME_LENGTH = "코치의 이름은 %d자 이상 %d자 이하로 입력해야합니다.";

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 4;

    private final String name;

    private CoachName(String name) {
        validate(name);
        this.name = name;
    }

    public static CoachName from(String input) {
        return new CoachName(input);
    }

    public String getName() {
        return this.name;
    }

    private void validate(String name) {
        if (isInvalidLength(name)) {
            throw new IllegalArgumentException(
                    String.format(ERROR_INVALID_COACH_NAME_LENGTH, MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
    }

    private static boolean isInvalidLength(String name) {
        return name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoachName coachName = (CoachName) o;
        return Objects.equals(name, coachName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
