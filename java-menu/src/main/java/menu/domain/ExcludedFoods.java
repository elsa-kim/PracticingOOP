package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExcludedFoods {
    private static final String ERROR_EXCLUDED_MENU_COUNT = "[ERROR] 못 먹는 메뉴는 최대 %d개 입력가능합니다.";
    private static final int MAX_EXCLUDED_COUNT = 2;

    private final List<Food> excludedFoods;

    private ExcludedFoods(List<Food> excludedFoods) {
        validate(excludedFoods);
        this.excludedFoods = excludedFoods;
    }

    public static ExcludedFoods from(String input) {
        if (input.isBlank()) {
            return new ExcludedFoods(new ArrayList<>());
        }
        List<Food> excludedFoods = Arrays.stream(input.split(",", -1))
                .map(Food::from)
                .collect(Collectors.toList());
        return new ExcludedFoods(excludedFoods);
    }

    public static ExcludedFoods init() {
        return new ExcludedFoods(new ArrayList<>());
    }

    private void validate(List<Food> excludedFoods) {
        if (excludedFoods.size() > MAX_EXCLUDED_COUNT) {
            throw new IllegalArgumentException(String.format(ERROR_EXCLUDED_MENU_COUNT, MAX_EXCLUDED_COUNT));
        }
    }

    public boolean isContain(Food food) {
        return excludedFoods.contains(food);
    }
}
