package menu.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.CoachName;
import menu.domain.ExcludedMenus;
import menu.domain.Menu;
import menu.domain.MenuCategory;
import menu.domain.RecommendCategories;

public class Recommender {

    public List<Coach> generate(RecommendCategories recommendCategories, Map<CoachName, ExcludedMenus> infoMap) {
        Map<CoachName, List<Menu>> recommendMenusMap = new LinkedHashMap<>();

        for (MenuCategory menuCategory : recommendCategories.getMenuCategories()) {
            infoMap.forEach((name, excludedMenus) -> {
                List<Menu> recommends = pickMenu(name, excludedMenus, recommendMenusMap, menuCategory);
                recommendMenusMap.put(name, recommends);
            });
        }

        return generateCoaches(recommendMenusMap, infoMap);
    }

    private List<Coach> generateCoaches(Map<CoachName, List<Menu>> recommends, Map<CoachName, ExcludedMenus> infoMap) {
        List<Coach> Coaches = new ArrayList<>();
        recommends.forEach((coachName, recommendMenus) -> {
            Coach coach = Coach.of(coachName, recommendMenus, infoMap.get(coachName));
            Coaches.add(coach);
        });
        return Coaches;
    }

    private List<Menu> pickMenu(CoachName coachName,
                                ExcludedMenus excludedMenus,
                                Map<CoachName, List<Menu>> recommendMenusMap,
                                MenuCategory pickedCategory) {
        List<Menu> recommends = recommendMenusMap.getOrDefault(coachName, new ArrayList<>());
        recommends.add(Menu.pickOne(pickedCategory, excludedMenus, recommends));
        return recommends;
    }
}
