package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarPositionTest {

    @Test
    void 팩토리메서드_create_를_통해_인스턴스를_생성한다(){
        // when
        CarPosition startPosition = CarPosition.create();

        // then
        assertThat(startPosition).isNotNull();
    }

    @Test
    void 이동여부를_결정해_CarPosition을_반환한다(){
        // given
        CarPosition position = CarPosition.create();

        // when
        CarPosition nextPosition = position.attemptMove();

        // then
        assertThat(nextPosition).isNotNull();
    }

}