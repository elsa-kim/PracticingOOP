package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Coach {
    private final String name;
    private final List<Food> cannotEatFoods;
    private final List<Food> recommendFoods;

    private Coach(String name, List<Food> cannotEatFoods, List<Food> recommendFoods) {
        this.name = name;
        this.cannotEatFoods = cannotEatFoods;
        this.recommendFoods = recommendFoods;
    }

    public static Coach from(String coachName) {
        return new Coach(coachName, new ArrayList<>(), new ArrayList<>());
    }

    public Coach create(String input) {
        List<Food> cannotEatFoods = Arrays.stream(input.split(","))
                .map(Food::from)
                .collect(Collectors.toList());
        validateCannotEatFoods(cannotEatFoods);
        return new Coach(name, cannotEatFoods, new ArrayList<>());
    }

    public Coach withRecommendedFoods(List<Food> recommendFoods) {
        return new Coach(name, cannotEatFoods, recommendFoods);
    }

    private static void validateCannotEatFoods(List<Food> cannotEatFoods) {
        if (cannotEatFoods.size() > 2) {
            throw new IllegalArgumentException("[ERROR] 못 먹는 메뉴는 최대 2개 입력가능합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Food> getRecommendFoods() {
        return List.copyOf(this.recommendFoods);
    }

    public boolean isCannotEat(Food pickedMenu) {
        return cannotEatFoods.contains(pickedMenu);
    }

    public boolean isAlreadyInRecommend(Food pickedMenu) {
        return recommendFoods.contains(pickedMenu);
    }
}
