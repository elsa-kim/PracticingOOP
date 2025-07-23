package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.function.Predicate.not;
import java.util.stream.Collectors;

public enum MenuCategory {
    JAPANESE("일식"),
    KOREAN("한식"),
    CHINESE("중식"),
    ASIAN("아시안"),
    WESTERN("양식");

    private final String label;

    MenuCategory(String label) {
        this.label = label;
    }

    public static MenuCategory pickOne(List<MenuCategory> menuCategories) {
        List<MenuCategory> availableCategories = Arrays.stream(MenuCategory.values())
                .filter(not(getAlreadySelectedCategories(menuCategories)::contains))
                .collect(Collectors.toList());

        int randomNumber = Randoms.pickNumberInRange(1, availableCategories.size()) - 1;
        return availableCategories.get(randomNumber);
    }

    private static List<MenuCategory> getAlreadySelectedCategories(List<MenuCategory> menuCategories) {
        Map<MenuCategory, Long> categoryCountMap = menuCategories.stream()
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

        return categoryCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    public String getLabel() {
        return this.label;
    }
}
