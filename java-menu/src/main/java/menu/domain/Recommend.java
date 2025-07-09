package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private final List<MenuCategory> menuCategories;
    private final Coaches coaches;

    public Recommend(List<MenuCategory> menuCategories, Coaches coaches) {
        this.menuCategories = menuCategories;
        this.coaches = coaches;
    }

    public static Recommend generate(Coaches coaches) {
        List<MenuCategory> menuCategories = new ArrayList<>();
        List<Coach> coachesWithRecommend = new ArrayList<>(coaches.getCoaches());
        for (int i = 0; i < 5; i++) {
            MenuCategory pickedCategory = MenuCategory.pickOne();
            while (isCategoryPickedTwice(menuCategories, pickedCategory)) {
                pickedCategory = MenuCategory.pickOne();
            }
            menuCategories.add(pickedCategory);

            for (int idx = 0; idx < coachesWithRecommend.size(); idx++) {
                Coach coach = coachesWithRecommend.get(idx);
                List<Food> foods = new ArrayList<>(coach.getRecommendFoods());
                Food pickedMenu = Food.pickOne(pickedCategory);
                while (coach.isExcludedFood(pickedMenu) || coach.isAlreadyInRecommend(pickedMenu)) {
                    pickedMenu = Food.pickOne(pickedCategory);
                }
                foods.add(pickedMenu);
                coachesWithRecommend.set(idx, coach.withRecommendedFoods(foods));
            }

        }
        Coaches updatedCoaches = Coaches.generate(coachesWithRecommend);

        return new Recommend(menuCategories, updatedCoaches);
    }

    private static boolean isCategoryPickedTwice(List<MenuCategory> menuCategories, MenuCategory pick) {
        return menuCategories.stream().filter(category -> category == pick).count() >= 2;
    }

    public List<MenuCategory> getMenuCategories() {
        return List.copyOf(menuCategories);
    }

    public List<Coach> getCoaches() {
        return coaches.getCoaches();
    }

}
