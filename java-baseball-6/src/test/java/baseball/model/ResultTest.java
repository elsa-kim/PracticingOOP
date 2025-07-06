package baseball.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void Result객체의_스트라이크_개수가_3개면_true를_반환한다() {
        // given
        Result result = new Result(0, 3);

        // when & then
        assertTrue(result.isThreeStrikes());
    }

    @Test
    void Result객체의_스트라이크_개수가_3개가_아니면_fasle를_반환한다() {
        // given
        Result result = new Result(1, 2);

        // when & then
        assertFalse(result.isThreeStrikes());
    }

}