package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class RecommendFoods {
    public static final String ERROR_DUPLICATE_MENU = "[ERROR] 이미 포함된 메뉴입니다.";

    private final List<Food> recommendFoods;

    private RecommendFoods(List<Food> recommendFoods) {
        validate(recommendFoods);
        this.recommendFoods = recommendFoods;
    }

    public static RecommendFoods init() {
        return new RecommendFoods(new ArrayList<>());
    }

    public RecommendFoods generate(List<Food> foods) {
        return new RecommendFoods(foods);
    }

    private void validate(List<Food> recommendFoods) {
        if (recommendFoods.stream().distinct().count() != recommendFoods.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MENU);
        }
    }

    public boolean isContain(Food pickedMenu) {
        return recommendFoods.contains(pickedMenu);
    }

    public List<Food> getRecommendedFoods() {
        return List.copyOf(recommendFoods);
    }
}
