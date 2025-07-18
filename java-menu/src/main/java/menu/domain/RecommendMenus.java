package menu.domain;

import java.util.List;

public class RecommendMenus {
    private static final String ERROR_DUPLICATE_MENU = "이미 포함된 메뉴입니다.";
    private static final String ERROR_INVALID_RECOMMENDED_MENU_COUNT = "추천된 메뉴 수가 올바르지 않습니다.";
    private static final int RECOMMENDED_MENU_COUNT = 5;

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
        validateDuplicated(recommendMenus);
        validateFoodsCount(recommendMenus.size());
    }

    private void validateFoodsCount(int recommendMenuCount) {
        if(recommendMenuCount != RECOMMENDED_MENU_COUNT){
            throw new IllegalArgumentException(ERROR_INVALID_RECOMMENDED_MENU_COUNT);
        }
    }

    private void validateDuplicated(List<Menu> recommendMenus) {
        if (isDuplicated(recommendMenus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MENU);
        }
    }

    private boolean isDuplicated(List<Menu> recommendMenus) {
        return recommendMenus.stream().distinct().count() != recommendMenus.size();
    }
}
