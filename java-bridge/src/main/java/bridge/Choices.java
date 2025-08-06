package bridge;

import java.util.List;
import java.util.Objects;

public class Choices {
    private final List<String> choices;

    public Choices(List<String> choices) {
        validate(choices);
        this.choices = choices;
    }

    private void validate(List<String> choices) {
        if(isInvalidFormat(choices)){
            throw new IllegalArgumentException("잘못된 선택입니다");
        };
    }

    private boolean isInvalidFormat(List<String> choices) {
        return choices.stream()
                .anyMatch(choice -> !Objects.equals("U", choice) && !Objects.equals("D", choice));
    }
}
