package menu.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.MenuCategory;
import menu.domain.RecommendCategories;

public class RecommendResponse {
    List<String> menuCategories;
    List<CoachResponse> coaches;

    private RecommendResponse(List<String> menuCategories, List<CoachResponse> coaches) {
        this.menuCategories = menuCategories;
        this.coaches = coaches;
    }

    public static RecommendResponse from(RecommendCategories recommendCategories, List<CoachResponse> coaches) {
        List<String> categories = recommendCategories.getMenuCategories().stream()
                .map(MenuCategory::getLabel)
                .collect(Collectors.toList());

        return new RecommendResponse(categories, coaches);
    }

    public List<String> getMenuCategories() {
        return List.copyOf(menuCategories);
    }

    public List<CoachResponse> getCoaches() {
        return List.copyOf(coaches);
    }
}
