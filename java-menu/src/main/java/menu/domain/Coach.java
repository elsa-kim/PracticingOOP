package menu.domain;

import java.util.List;

public class Coach {
    private final CoachName name;
    private final RecommendMenus recommendMenus;

    private Coach(CoachName name, RecommendMenus recommendMenus) {
        this.name = name;
        this.recommendMenus = recommendMenus;
    }

    public static Coach of(CoachName name, List<Menu> recommendMenus) {
        return new Coach(name, RecommendMenus.of(recommendMenus));
    }

    public String getName() {
        return this.name.getName();
    }

    public List<Menu> getRecommendFoods() {
        return this.recommendMenus.getRecommendedFoods();
    }
}
