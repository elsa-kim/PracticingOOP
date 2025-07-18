package menu.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Coaches {
    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public static Coaches generate(RecommendCategories recommendCategories, Map<CoachName, ExcludedMenus> infoMap) {
        Map<CoachName, List<Menu>> recommendMenusMap = new LinkedHashMap<>();

        for (MenuCategory menuCategory : recommendCategories.getMenuCategories()) {
            infoMap.forEach((name, excludedMenus) -> {
                List<Menu> recommends = pickMenu(name, excludedMenus, recommendMenusMap, menuCategory);
                recommendMenusMap.put(name, recommends);
            });
        }

        List<Coach> Coaches = generateCoaches(recommendMenusMap, infoMap);

        return new Coaches(Coaches);
    }

    public List<Coach> getCoaches() {
        return List.copyOf(coaches);
    }

    private static List<Coach> generateCoaches(Map<CoachName, List<Menu>> recommends, Map<CoachName, ExcludedMenus> infoMap) {
        List<Coach> Coaches = new ArrayList<>();
        recommends.forEach((coachName, recommendMenus) -> {
            Coach coach = Coach.of(coachName, recommendMenus, infoMap.get(coachName));
            Coaches.add(coach);
        });
        return Coaches;
    }

    private static List<Menu> pickMenu(CoachName coachName,
                                       ExcludedMenus excludedMenus,
                                       Map<CoachName, List<Menu>> recommendMenusMap,
                                       MenuCategory pickedCategory) {
        List<Menu> recommends = recommendMenusMap.getOrDefault(coachName, new ArrayList<>());

        Menu pickedMenu = Menu.pickOne(pickedCategory);
        while (cannotAdd(excludedMenus, recommends, pickedMenu)) {
            pickedMenu = Menu.pickOne(pickedCategory);
        }
        recommends.add(pickedMenu);
        return recommends;
    }

    private static boolean cannotAdd(ExcludedMenus excludedMenus, List<Menu> menus, Menu pickedMenu) {
        return excludedMenus.isContain(pickedMenu) || menus.contains(pickedMenu);
    }
}
