package menu.dto.request;

import java.util.ArrayList;
import java.util.List;

public class ExcludedFoodsRequest {
    private final List<String> foods;

    private ExcludedFoodsRequest(List<String> foods) {
        this.foods = foods;
    }

    public static ExcludedFoodsRequest from(String input) {
        if (input.isBlank()) {
            return new ExcludedFoodsRequest(new ArrayList<>());
        }
        return new ExcludedFoodsRequest(List.of(input.split(",", -1)));
    }

    public List<String> getFoods() {
        return List.copyOf(foods);
    }
}
