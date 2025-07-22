package menu.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

class RecommendCategoriesTest {

    @Test
    void 팩토리메서드_generate_통해_인스턴스를_생성한다() {
        // when
        RecommendCategories categories = RecommendCategories.generate();

        // then
        assertAll(
                () -> assertThat(categories).isNotNull(),
                () -> assertThat(categories.getMenuCategories().size()).isEqualTo(5)
        );
    }
}