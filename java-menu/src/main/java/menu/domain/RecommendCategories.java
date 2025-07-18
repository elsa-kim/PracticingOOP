package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class RecommendCategories {
    private static final String ERROR_INVALID_RECOMMENDED_CATEGORY_COUNT = "추천된 카테고리 수는 반드시 %d개여야 합니다.";
    private static final String ERROR_DUPLICATE_CATEGORY_LIMIT_EXCEEDED = "동일한 카테고리는 최대 %d번까지 선택할 수 있습니다.";
    private static final int WEEKLY_MENU_CATEGORY_COUNT = 5;
    private static final int MAX_DUPLICATE_CATEGORY_COUNT = 2;

    private final List<MenuCategory> menuCategories;

    private RecommendCategories(List<MenuCategory> menuCategories) {
        validate(menuCategories);
        this.menuCategories = menuCategories;
    }

    public static RecommendCategories generate() {
        List<MenuCategory> menuCategories = new ArrayList<>();
        while (menuCategories.size() < WEEKLY_MENU_CATEGORY_COUNT) {
            MenuCategory pickedCategory = MenuCategory.pickOne();
            if (isCategoryPickedTwice(menuCategories, pickedCategory)) {
                continue;
            }
            menuCategories.add(pickedCategory);
        }
        return new RecommendCategories(menuCategories);
    }

    public List<MenuCategory> getMenuCategories() {
        return List.copyOf(menuCategories);
    }

    private static boolean isCategoryPickedTwice(List<MenuCategory> menuCategories, MenuCategory pick) {
        return menuCategories.stream()
                .filter(category -> category == pick)
                .count() >= MAX_DUPLICATE_CATEGORY_COUNT;
    }

    private void validate(List<MenuCategory> menuCategories) {
        validateCategoriesCount(menuCategories);
        validateDuplicated(menuCategories);
    }

    private static void validateDuplicated(List<MenuCategory> menuCategories) {
        for (MenuCategory menuCategory : menuCategories) {
            if (isExceedingDuplicateLimit(menuCategories, menuCategory)) {
                throw new IllegalArgumentException(
                        String.format(ERROR_DUPLICATE_CATEGORY_LIMIT_EXCEEDED, MAX_DUPLICATE_CATEGORY_COUNT));
            }
        }
    }

    private static boolean isExceedingDuplicateLimit(List<MenuCategory> menuCategories, MenuCategory menuCategory) {
        return menuCategories.stream()
                .filter(category -> category == menuCategory)
                .count() > MAX_DUPLICATE_CATEGORY_COUNT;
    }

    private void validateCategoriesCount(List<MenuCategory> menuCategories) {
        if (menuCategories.size() != WEEKLY_MENU_CATEGORY_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ERROR_INVALID_RECOMMENDED_CATEGORY_COUNT, WEEKLY_MENU_CATEGORY_COUNT));
        }
    }
}
