package racingcar.domain;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class RaceCountTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", " 3"})
    void 팩토리메서드_fromInput을_통해_인스턴스를_생성(String input) {
        // when
        RaceCount race = RaceCount.fromInput(input);

        // then
        assertThat(race).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "1a", "a3", "3 0"})
    void 인스턴스를_생성시_숫자가_아닌값을_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> RaceCount.fromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자를 입력해야합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-3"})
    void 인스턴스를_생성시_양수의_값이_아닌_수를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> RaceCount.fromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 이상의 수를 입력해야합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    void 레이싱_횟수와_입력값이_동일한지_판단(RaceCount raceCount, int tryCnt, boolean expected) {
        // when
        boolean result = raceCount.isFinish(tryCnt);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                arguments(RaceCount.fromInput("3"), 1, false),
                arguments(RaceCount.fromInput("3"), 3, true)
        );
    }

}