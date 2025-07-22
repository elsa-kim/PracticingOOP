package menu.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoachNames {
    private static final String ERROR_DUPLICATE_COACH_NAME = "중복된 이름을 입력할 수 없습니다.";
    private static final String ERROR_COACH_COUNT = "코치는 %d명 이상 %d명 이하로 입력해야 합니다.";

    private static final int MIN_COACH_COUNT = 2;
    private static final int MAX_COACH_COUNT = 5;

    private final List<CoachName> coachNames;

    private CoachNames(List<CoachName> coachNames) {
        validate(coachNames);
        this.coachNames = coachNames;
    }

    public static CoachNames from(String coachesName) {
        List<CoachName> names = Arrays.stream(coachesName.split(",", -1))
                .map(CoachName::from)
                .collect(Collectors.toList());

        return new CoachNames(names);
    }

    public List<CoachName> getCoachNames() {
        return List.copyOf(coachNames);
    }

    private void validate(List<CoachName> names) {
        validateDuplicate(names);
        validateCoachCount(names.size());
    }

    private void validateDuplicate(List<CoachName> names) {
        if (isDuplicate(names)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_COACH_NAME);
        }
    }

    private void validateCoachCount(int coachesNumber) {
        if (coachesNumber < MIN_COACH_COUNT || coachesNumber > MAX_COACH_COUNT) {
            throw new IllegalArgumentException(String.format(ERROR_COACH_COUNT, MIN_COACH_COUNT, MAX_COACH_COUNT));
        }
    }

    private boolean isDuplicate(List<CoachName> names) {
        return names.stream().distinct().count() != names.size();
    }
}
