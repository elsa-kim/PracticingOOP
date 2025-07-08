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
        MenuCategory pickedCategory = MenuCategory.pickOne();
        while(isCategoryPickedTwice(menuCategories, pickedCategory)){
            pickedCategory = MenuCategory.pickOne();
        }
        menuCategories.add(pickedCategory);

        List<Coach> CoachesWithRecommend = new ArrayList<>();
        for(Coach coach : coaches.getCoaches()) {
            List<Food> foods = new ArrayList<>(coach.getRecommendFoods());
            Food pickedMenu = Food.pickOne(pickedCategory);
            while(coach.isCannotEat(pickedMenu) || coach.isAlreadyInRecommend(pickedMenu)){
                pickedMenu = Food.pickOne(pickedCategory);
            }
            foods.add(pickedMenu);
            CoachesWithRecommend.add(coach.withRecommendedFoods(foods));
        }
        Coaches updatedCoaches = Coaches.generate(CoachesWithRecommend);

        return new Recommend(menuCategories, updatedCoaches);
    }

    private static boolean isCategoryPickedTwice(List<MenuCategory> menuCategories, MenuCategory pick) {
        return menuCategories.stream().filter(category -> category == pick).count() >= 2;
    }


}
