package menu.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Coach {
    private final String name;
    private final List<Food> cannotEatFoods;

    private Coach(String name, List<Food> cannotEatFoods) {
        this.name = name;
        this.cannotEatFoods = cannotEatFoods;
    }

    public static Coach from(String coachName) {
        return new Coach(coachName, null);
    }

    public Coach create(String input) {
        List<Food> cannotEatFoods = Arrays.stream(input.split(","))
                .map(Food::from)
                .collect(Collectors.toList());
        validateCannotEatFoods(cannotEatFoods);
        return new Coach(name, cannotEatFoods);
    }

    private static void validateCannotEatFoods(List<Food> cannotEatFoods) {
        if (cannotEatFoods.size() > 2) {
            throw new IllegalArgumentException("[ERROR] 못 먹는 메뉴는 최대 2개 입력가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
