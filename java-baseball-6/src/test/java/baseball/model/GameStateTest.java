package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameStateTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void 정적_팩토리_메서드_from_으로_인스턴스를_생성한다(String input) {
        // when
        GameState state = GameState.from(input);

        // then
        assertThat(state).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "0", "aa"})
    void 지정값_외의_값_입력시_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> GameState.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}