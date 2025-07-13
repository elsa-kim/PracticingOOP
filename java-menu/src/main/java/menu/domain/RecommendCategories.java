package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class RecommendCategories {
    private static final int WEEKLY_MENU_CATEGORY_COUNT = 5;

    private final List<MenuCategory> menuCategories;

    private RecommendCategories(List<MenuCategory> menuCategories) {
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
        return menuCategories.stream().filter(category -> category == pick).count() >= 2;
    }
}
