package menu.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachNameTest {

    @Test
    void 팩토리메서드_from으로_인스턴스를_생성한다() {
        // given
        String name = "토미";

        // when
        CoachName coachName = CoachName.from(name);

        // then
        assertThat(coachName).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"포", "포코포코포"})
    void 코치명이_2자이상_4자이하_조건을_불만족하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachName.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("코치의 이름은 2자 이상 4자 이하로 입력해야합니다.");
    }

}