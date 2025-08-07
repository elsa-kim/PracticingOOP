package bridge;

import java.util.List;
import java.util.Objects;

public class Choices {
    private final List<String> choices;

    public Choices(List<String> choices) {
        validate(choices);
        this.choices = choices;
    }


}
