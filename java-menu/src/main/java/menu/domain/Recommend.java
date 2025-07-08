package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recommend {
    private final List<MenuCategory> menuCategories;
    private final List<Food> foods;

    public Recommend(List<MenuCategory> menuCategories, List<Food> foods) {
        this.menuCategories = menuCategories;
        this.foods = foods;
    }

    public static Recommend generate() {
        List<MenuCategory> menuCategories = new ArrayList<>();
        MenuCategory pickedCategory = MenuCategory.pickOne();
        while(isCategoryPickedTwice(menuCategories, pickedCategory)){
            pickedCategory = MenuCategory.pickOne();
        }
        menuCategories.add(pickedCategory);
        List<Food> foods = new ArrayList<>();
        
        return new Recommend(menuCategories, foods);
    }

    private static boolean isCategoryPickedTwice(List<MenuCategory> menuCategories, MenuCategory pick) {
        return menuCategories.stream().filter(category -> category == pick).count() >= 2;
    }


}
