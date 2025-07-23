package menu.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void 메뉴_이름을_받아서_Menu를_반환한다() {
        // given
        String input = "우동";

        // when
        Menu menu = Menu.from(input);

        // then
        assertThat(menu).isEqualTo(Menu.UDON);
    }

    @Test
    void 존재하지_않는_메뉴이름을_입력시_예외를_반환한다() {
        // given
        String input = "짜파게티";

        // when & then
        assertThatThrownBy(() -> Menu.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 메뉴입니다.");
    }

    @Test
    void 전달한_카테고리에_해당하는_추천메뉴를_반환한다(){
        // given
        MenuCategory category = MenuCategory.KOREAN;
        ExcludedMenus excludedMenus = ExcludedMenus.from("우동");
        List<Menu> pickedMenus = List.of();

        // when
        Menu pickedMenu = Menu.pickOne(category, excludedMenus, pickedMenus);

        // then
        assertThat(pickedMenu).isNotNull();
    }
}