package menu.domain;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ExcludedMenusTest {

    @ParameterizedTest
    @ValueSource(strings = {"우동", "", "우동,뇨끼"})
    void 팩토리메서드_from으로_인스턴스를_생성한다(String menuName) {
        // when
        ExcludedMenus excludedMenus = ExcludedMenus.from(menuName);

        // then
        assertThat(excludedMenus).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"우동,피자,뇨끼", "쌀국수,반미,분짜,파니니"})
    void 못먹는_메뉴_2개_초과입력시_예외가_발생한다(String menuName) {
        // when & then
        assertThatThrownBy(() -> ExcludedMenus.from(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("못 먹는 메뉴는 최대 2개 입력가능합니다.");
    }

    @Test
    void 못먹는_메뉴_입력에_중복_존재시_예외가_발생한다() {
        // given
        String menuNames = "우동,우동";

        // when & then
        assertThatThrownBy(() -> ExcludedMenus.from(menuNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("메뉴를 중복 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"우동,", "우동,순두부찌개", "순두부찌개"})
    void 존재하지_않는_메뉴_입력시_예외가_발생한다(String menuName) {
        // when & then
        assertThatThrownBy(() -> ExcludedMenus.from(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 메뉴입니다.");
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                arguments(ExcludedMenus.from("우동,뇨끼"), Menu.from("우동"), true),
                arguments(ExcludedMenus.from("우동,뇨끼"), Menu.from("반미"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    void ExcludedMenus_객체에_전달한_메뉴가_존재하는지_확인한다(ExcludedMenus excludedMenus, Menu checkMenu, boolean expected) {
        // when
        boolean result = excludedMenus.isContain(checkMenu);

        // then
        assertThat(result).isEqualTo(expected);
    }

}