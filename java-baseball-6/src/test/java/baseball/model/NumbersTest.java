package baseball.model;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumbersTest {

    @ParameterizedTest
//    @EnumSource(GameState.class)
//    @NullSource
//    @MethodSource("provideInputNumbers")
//    @CsvSource(value = {"123,false", "345,true"})
    @ValueSource(strings = {"123", "935", "719"})
    void 정적_팩토리_메서드_fromString_으로_인스턴스를_생성한다(String input) {
        // when
        Numbers numbers = Numbers.fromString(input);

        // then
        assertThat(numbers).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a", "a13"})
    void 숫자가_아닌_값을_입력하면_예외가_발생한댜(String input) {
        // when & then
        assertThatThrownBy(() -> Numbers.fromString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자만 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"023", "109", "490"})
    void 범위_밖의_숫자를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Numbers.fromString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 이상 9 이하의 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"223", "222", "898"})
    void 입력값에_중복이_있을경우_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Numbers.fromString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복값이 존재하면 안됩니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2345", "35", "5"})
    void 입력값의_길이가_3이_아니면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Numbers.fromString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("3자리 숫자를 입력해야 합니다.");
    }

    @Test
    void 정적_팩토리_메서드_generateRandomNumbers_로_타겟을_생성한다() {
        // when
        Numbers number = Numbers.generateRandomNumbers();

        // then
        assertThat(number).isNotNull();
    }


    @ParameterizedTest
    @MethodSource("provideValue")
    void Numbers_객체_비교하여_Result_객체를_반환(Numbers target, Numbers userNumber, Result expected) {
        // when
        Result result = target.compareWith(userNumber);

        // then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> provideValue() {
        return Stream.of(
                arguments(Numbers.fromString("123"), Numbers.fromString("135"), new Result(1, 1)),
                arguments(Numbers.fromString("123"), Numbers.fromString("456"), new Result(0, 0)),
                arguments(Numbers.fromString("123"), Numbers.fromString("129"), new Result(0, 2)),
                arguments(Numbers.fromString("123"), Numbers.fromString("312"), new Result(3, 0)),
                arguments(Numbers.fromString("123"), Numbers.fromString("123"), new Result(0, 3))
        );
    }
}