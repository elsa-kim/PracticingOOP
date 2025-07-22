package menu.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachNamesTest {
    @Test
    void 팩토리메서드_from으로_인스턴스_생성한다() {
        // given
        String input = "토미,제임스,포코";

        // when
        CoachNames names = CoachNames.from(input);

        // then
        assertThat(names.getCoachNames().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"토미,토미,포코", "토미,토미"})
    void 코치이름을_중복입력시_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachNames.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 입력할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"토미", "토미,포코,aa,bb,cc,dd"})
    void 코치이름을_2명_미만_5명_초과_입력시_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachNames.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("코치는 2명 이상 5명 이하로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"토미,포", "토미,포코포코포"})
    void 입력값중_2자이상_4자이하_조건을_불만족하는_코치명_존재시_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachNames.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("코치의 이름은 2자 이상 4자 이하로 입력해야합니다.");
    }
}