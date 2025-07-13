package menu.domain;

import java.util.List;

public class RecommendMenus {
    private static final String ERROR_DUPLICATE_MENU = "이미 포함된 메뉴입니다.";

    private final List<Menu> recommendMenus;

    private RecommendMenus(List<Menu> recommendMenus) {
        validate(recommendMenus);
        this.recommendMenus = recommendMenus;
    }

    public static RecommendMenus of(List<Menu> menus) {
        return new RecommendMenus(menus);
    }

    public List<Menu> getRecommendedFoods() {
        return List.copyOf(recommendMenus);
    }

    private void validate(List<Menu> recommendMenus) {
        if (recommendMenus.stream().distinct().count() != recommendMenus.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MENU);
        }
    }
}
