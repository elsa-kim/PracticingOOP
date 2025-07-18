package menu.domain;

import java.util.List;

public class Coach {
    private static final String ERROR_EXCLUDED_MENU_INCLUDED = "허용되지 않은 메뉴가 포함되어 있습니다.";

    private final CoachName name;
    private final RecommendMenus recommendMenus;

    private Coach(CoachName name, RecommendMenus recommendMenus, ExcludedMenus excludedMenus) {
        validate(recommendMenus, excludedMenus);
        this.name = name;
        this.recommendMenus = recommendMenus;
    }

    public static Coach of(CoachName name, List<Menu> recommendMenus, ExcludedMenus excludedMenus) {
        return new Coach(name, RecommendMenus.of(recommendMenus), excludedMenus);
    }

    public String getName() {
        return this.name.getName();
    }

    public List<Menu> getRecommendFoods() {
        return this.recommendMenus.getRecommendedFoods();
    }

    private void validate(RecommendMenus recommendMenus, ExcludedMenus excludedMenus) {
        if(isIncludeExcludedMenu(recommendMenus, excludedMenus)){
            throw new IllegalArgumentException(ERROR_EXCLUDED_MENU_INCLUDED);
        }
    }

    private boolean isIncludeExcludedMenu(RecommendMenus recommendMenus, ExcludedMenus excludedMenus) {
        return recommendMenus.getRecommendedFoods().stream()
                .anyMatch(excludedMenus::isContain);
    }
}
