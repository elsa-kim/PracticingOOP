package racingcar.domain;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CarPositionTest {

    @Test
    void 팩토리메서드_create_를_통해_인스턴스를_생성한다() {
        // when
        CarPosition startPosition = CarPosition.create();

        // then
        assertThat(startPosition).isNotNull();
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                arguments(0, true),
                arguments(3, true),
                arguments(4, false),
                arguments(9, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    void 이동여부를_결정해_CarPosition을_반환한다(int moveConditionValue, boolean isSamePosition) {
        // given
        CarPosition position = CarPosition.create();

        // when
        CarPosition nextPosition = position.attemptMove(moveConditionValue);

        // then
        assertThat(nextPosition.equals(position)).isEqualTo(isSamePosition);
    }

}