package menu.domain;

import java.util.List;
import static menu.domain.MenuCategory.ASIAN;
import static menu.domain.MenuCategory.CHINESE;
import static menu.domain.MenuCategory.JAPANESE;
import static menu.domain.MenuCategory.KOREAN;
import static menu.domain.MenuCategory.WESTERN;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class MenuCategoryTest {

    @Test
    void 메뉴카테고리_중_추가_가능한_카테고리를_반환한다() {
        // given
        List<MenuCategory> menuCategories = List.of(ASIAN, KOREAN);

        // when
        MenuCategory category = MenuCategory.pickOne(menuCategories);

        // then
        assertThat(category).isNotNull();
    }

    @Test
    void 카테고리리스트에_이미_두번_포함된_카테고리를_제외하고_랜덤으로_반환한다() {
        // given
        List<MenuCategory> menuCategories = List.of(ASIAN, ASIAN, KOREAN, KOREAN, CHINESE, CHINESE, JAPANESE, JAPANESE);

        // when
        MenuCategory category = MenuCategory.pickOne(menuCategories);

        // then
        assertThat(category).isEqualTo(WESTERN);
    }

}