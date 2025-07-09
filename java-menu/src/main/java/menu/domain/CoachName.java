package menu.domain;

public class CoachName {
    private static final String ERROR_INVALID_COACH_NAME_LENGTH = "[ERROR] 코치의 이름은 %d자 이상 %d자 이하로 입력해야합니다.";
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

    private void validate(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(ERROR_INVALID_COACH_NAME_LENGTH, MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
    }

    public String getName() {
        return this.name;
    }
}
