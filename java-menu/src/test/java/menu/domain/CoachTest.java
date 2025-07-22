package menu.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class CoachTest {

    @Test
    void 팩토리메서드_of_로_인스턴스를_생성한다() {
        // given
        CoachName name = CoachName.from("포코");
        List<Menu> recommendMenus = List.of(
                Menu.BAGUETTE, Menu.HAYASHI_RICE, Menu.BIBIMBAP, Menu.DOENJANG_STEW, Menu.GOCHU_JAPCHAE);
        ExcludedMenus excludedMenus = ExcludedMenus.from("");

        // when
        Coach coach = Coach.of(name, recommendMenus, excludedMenus);

        // then
        assertThat(coach).isNotNull();
    }

    @Test
    void 추천메뉴에_제외메뉴가_포함된_경우_예외가_발생한다() {
        // given
        CoachName name = CoachName.from("포코");
        List<Menu> recommendMenus = List.of(
                Menu.BAGUETTE, Menu.HAYASHI_RICE, Menu.BIBIMBAP, Menu.DOENJANG_STEW, Menu.GOCHU_JAPCHAE);
        ExcludedMenus excludedMenus = ExcludedMenus.from("비빔밥");

        // when & then
        assertThatThrownBy(() -> Coach.of(name, recommendMenus, excludedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않은 메뉴가 포함되어 있습니다.");
    }
}