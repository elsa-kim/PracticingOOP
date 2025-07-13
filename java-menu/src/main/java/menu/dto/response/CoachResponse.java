package menu.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.Menu;

public class CoachResponse {
    String coachName;
    List<String> recommendMenus;

    private CoachResponse(String coachName, List<String> recommendMenus) {
        this.coachName = coachName;
        this.recommendMenus = recommendMenus;
    }

    public static CoachResponse from(Coach coach) {
        List<String> menus = coach.getRecommendFoods().stream()
                .map(Menu::getLabel)
                .collect(Collectors.toList());

        return new CoachResponse(coach.getName(), menus);
    }

    public String getCoachName() {
        return coachName;
    }

    public List<String> getRecommendMenus() {
        return List.copyOf(recommendMenus);
    }
}
