package menu.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {"포비,", "포비,  ,코비"})
    void 코치명_입력값에_공백이_존재하면_예외를_반환한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputView.validateNoBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 포함한 입력은 허용되지 않습니다.");
    }
}