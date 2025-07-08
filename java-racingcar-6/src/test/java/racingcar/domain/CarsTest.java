package racingcar.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarsTest {

    @Test
    void 팩토리메서드_generateCars_로_인스턴스를_생성한다() {
        // given
        String input = "aa,bb,cc";

        // when
        Cars cars = Cars.generateCars(input);

        // then
        assertThat(cars).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa,,bb", "aa,bb,", ",aa,bb,cc", "aa,bb,cccccc"})
    void 차_이름_글자수_1미만_5초과하여_넣으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Cars.generateCars(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1글자 이상 5글자 이하로 작성해야 합니다.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"aa,bb, ", "aa,bb,   "})
    void 차_이름에_공백만_넣으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Cars.generateCars(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름에 문자를 포함해야합니다.");

    }

    @Test
    void 자동차_별_전진된_위치_또는_동일한_위치로_변경된_Cars_반환(){
        // given
        Cars cars = Cars.generateCars("aa,bb,cc");

        // when
        Cars nextTurnCars = cars.goOrStop();

        // then
        assertThat(nextTurnCars).isNotNull();
    }

    @Test
    void 경주에서_우승한_자동차리스트를_반환한다(){
        // given
        Cars cars = Cars.generateCars("aa,bb,cc");

        // when
        List<Car> winCars = cars.getResult();

        // then
        assertThat(winCars.size()).isPositive();
    }

}