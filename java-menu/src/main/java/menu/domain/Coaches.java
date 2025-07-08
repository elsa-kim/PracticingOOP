package menu.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Coaches {
    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        validate(coaches);
        this.coaches = coaches;
    }

    public static Coaches from(String coachName) {
        List<String> names = List.of(coachName.split(","));
        return new Coaches(names.stream()
                .map(Coach::from)
                .collect(Collectors.toList()));
    }

    public static Coaches generate(List<Coach> updateCoaches) {
        return new Coaches(updateCoaches);
    }

    private void validate(List<Coach> coaches) {
        validateCoachesNumber(coaches.size());
        validateDuplication(coaches);
    }

    private void validateDuplication(List<Coach> coaches) {
        if(isDuplicate(coaches)) {
            throw new IllegalArgumentException("[ERROR] 중복된 코치 이름을 입력할 수 없습니다.");
        }
    }

    private boolean isDuplicate(List<Coach> coaches) {
        return coaches.stream().distinct().count() != coaches.size();
    }

    private void validateCoachesNumber(int coachesNumber) {
        if (coachesNumber < 2) {
            throw new IllegalArgumentException("[ERROR] 코치는 최소 2명 이상 입력해야 합니다.");
        }
        if (coachesNumber > 5) {
            throw new IllegalArgumentException("[ERROR] 코치는 최대 5명 이하 입력해야 합니다.");
        }
    }

    public List<Coach> getCoaches() {
        return List.copyOf(this.coaches);
    }
}
