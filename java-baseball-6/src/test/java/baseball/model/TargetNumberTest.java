package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class TargetNumberTest {

    @Test
    void 타겟과_Number객체_값을_비교하여_Result_객체를_반환한다() {
        // given
        TargetNumber targetNumber = new TargetNumber();
        Numbers number = Numbers.fromString("123");

        // when
        Result result = targetNumber.compareWith(number);

        // then
        assertThat(result).isNotNull();
    }

}