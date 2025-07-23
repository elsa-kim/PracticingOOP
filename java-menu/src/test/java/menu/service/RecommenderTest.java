package menu.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.CoachName;
import menu.domain.ExcludedMenus;
import menu.domain.RecommendCategories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RecommenderTest {

    private final Recommender recommender = new Recommender();

    @Test
    void 코치리스트를_생성한다(){
        // given
        RecommendCategories recommendCategories = RecommendCategories.generate();
        Map<CoachName, ExcludedMenus> infoMap = Map.of(
                CoachName.from("코비"), ExcludedMenus.from("우동"),
                CoachName.from("포비"), ExcludedMenus.from("")
        );

        // when
        List<Coach> coaches = recommender.generate(recommendCategories, infoMap);

        // then
        assertThat(coaches).hasSize(2)
                .extracting(Coach::getName, coach -> coach.getRecommendMenus().getRecommendedFoods().size())
                .containsExactlyInAnyOrder(
                        tuple("코비", 5),
                        tuple("포비", 5)
                );
    }

}