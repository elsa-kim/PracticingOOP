package menu.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Coaches {
    private static final String ERROR_DUPLICATE_COACH_NAME = "[ERROR] 코치 이름을 중복하여 입력할 수 없습니다.";
    private static final String ERROR_MINIMUM_COACH_COUNT = "[ERROR] 코치는 최소 %d명 이상 입력해야 합니다.";
    private static final String ERROR_MAXIMUM_COACH_COUNT = "[ERROR] 코치는 최대 %d명 이하 입력해야 합니다.";
    private static final int MIN_COACH_COUNT = 2;
    private static final int MAX_COACH_COUNT = 5;

    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        validate(coaches);
        this.coaches = coaches;
    }

    public static Coaches from(String coachName) {
        List<String> names = List.of(coachName.split(",", -1));
        validateDuplication(names);
        return new Coaches(names.stream()
                .map(Coach::from)
                .collect(Collectors.toList()));
    }

    public static Coaches generate(List<Coach> updateCoaches) {
        return new Coaches(updateCoaches);
    }

    private void validate(List<Coach> coaches) {
        validateMinimumCoachCount(coaches.size());
        validateMaximumCoachCount(coaches.size());
    }

    private static void validateDuplication(List<String> names) {
        if (isDuplicate(names)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_COACH_NAME);
        }
    }

    private static boolean isDuplicate(List<String> names) {
        return names.stream().distinct().count() != names.size();
    }

    private void validateMinimumCoachCount(int coachesNumber) {
        if (coachesNumber < MIN_COACH_COUNT) {
            throw new IllegalArgumentException(String.format(ERROR_MINIMUM_COACH_COUNT, MIN_COACH_COUNT));
        }
    }

    private void validateMaximumCoachCount(int coachesNumber) {
        if (coachesNumber > MAX_COACH_COUNT) {
            throw new IllegalArgumentException(String.format(ERROR_MAXIMUM_COACH_COUNT, MAX_COACH_COUNT));
        }
    }

    public List<Coach> getCoaches() {
        return List.copyOf(this.coaches);
    }
}
