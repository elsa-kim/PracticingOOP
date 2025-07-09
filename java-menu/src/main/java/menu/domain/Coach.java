package menu.domain;

import java.util.List;

public class Coach {
    private final CoachName name;
    private final ExcludedFoods excludedFoods;
    private final RecommendFoods recommendFoods;

    private Coach(CoachName name, ExcludedFoods excludedFoods, RecommendFoods recommendFoods) {
        this.name = name;
        this.excludedFoods = excludedFoods;
        this.recommendFoods = recommendFoods;
    }

    public static Coach from(String input) {
        return new Coach(CoachName.from(input), ExcludedFoods.init(), RecommendFoods.init());
    }

    public Coach create(String input) {
        return new Coach(this.name, ExcludedFoods.from(input), this.recommendFoods);
    }

    public Coach withRecommendedFoods(List<Food> recommendFoods) {
        return new Coach(this.name, excludedFoods, this.recommendFoods.generate(recommendFoods));
    }

    public String getName() {
        return this.name.getName();
    }

    public List<Food> getRecommendFoods() {
        return this.recommendFoods.getRecommendedFoods();
    }

    public boolean isExcludedFood(Food pickedMenu) {
        return excludedFoods.isContain(pickedMenu);
    }

    public boolean isAlreadyInRecommend(Food pickedMenu) {
        return recommendFoods.isContain(pickedMenu);
    }
}
