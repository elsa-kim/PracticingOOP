package menu.domain;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RecommendMenusTest {

    @Test
    void 팩토리메서드_of_로_인스턴스를_생성한다() {
        // given
        List<Menu> menus = List.of(
                Menu.BAGUETTE, Menu.QUICHE, Menu.HAYASHI_RICE, Menu.GOCHU_JAPCHAE, Menu.BIBIMBAP);

        // when
        RecommendMenus recommendMenus = RecommendMenus.of(menus);

        // then
        assertThat(recommendMenus).isNotNull();
    }

    @Test
    void 추천메뉴에_동일한_메뉴가_포함되어있으면_예외가_발생한다() {
        // given
        List<Menu> menus = List.of(
                Menu.BAGUETTE, Menu.BAGUETTE, Menu.HAYASHI_RICE, Menu.GOCHU_JAPCHAE, Menu.BIBIMBAP);

        // when
        assertThatThrownBy(() -> RecommendMenus.of(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("추천된 메뉴에 중복이 있습니다.");
    }

    public static Stream<Arguments> provideValue() {
        return Stream.of(
                arguments(List.of()),
                arguments(List.of(Menu.BAGUETTE)),
                arguments(List.of(Menu.BAGUETTE, Menu.HAYASHI_RICE)),
                arguments(List.of(Menu.BAGUETTE, Menu.QUICHE, Menu.HAYASHI_RICE)),
                arguments(List.of(Menu.BAGUETTE, Menu.QUICHE, Menu.HAYASHI_RICE, Menu.GOCHU_JAPCHAE)),
                arguments(List.of(Menu.BAGUETTE, Menu.QUICHE, Menu.HAYASHI_RICE, Menu.GOCHU_JAPCHAE,
                        Menu.BIBIMBAP, Menu.BULGOGI))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValue")
    void 추천메뉴_수가_5개가_아니면_예외가_발생한다(List<Menu> menus) {
        // when & then
        assertThatThrownBy(() -> RecommendMenus.of(menus)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("추천된 메뉴 수가 올바르지 않습니다.");
    }

}