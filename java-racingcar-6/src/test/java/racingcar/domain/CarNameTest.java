package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarNameTest {

    @Test
    void 팩토리메서드_from_으로_인스턴스_생성한다() {
        // when
        CarName name = CarName.from("myCar");

        // then
        assertThat(name).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaa"})
    void 팩토리메서드_from_에_빈값_또는_5자_이상_값을_넣으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CarName.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1글자 이상 5글자 이하로 작성해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   "})
    void 팩토리메서드_from_에_공백값을_넣으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CarName.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름에 문자를 포함해야합니다.");
    }
}