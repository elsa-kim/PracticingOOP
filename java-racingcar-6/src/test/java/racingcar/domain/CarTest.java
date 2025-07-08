package racingcar.domain;

import java.util.Objects;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {
    @Test
    void 정적팩토리메서드_createCar_을_통해_인스턴스를_생성한다() {
        // given
        String carName = "myCar";

        // when
        Car car = Car.createCar(carName);

        // then
        assertThat(car).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaa", "123456"})
    void 정적팩토리메서드_createCar_에_빈값_또는_5자_이상_값을_넣으면_예외가_빌생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Car.createCar(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1글자 이상 5글자 이하로 작성해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   "})
    void 정적팩토리메서드_createCar_에_공백값을_넣으면_예외가_빌생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Car.createCar(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름에 문자를 포함해야합니다.");
    }

    @Test
    void 전진_또는_정지를_판단하여_Car_객체를_반환한다() {
        // given
        Car car = Car.createCar("myCar");

        // when
        Car movedCar = car.goOrStop();

        // then
        assertAll(
                () -> assertThat(movedCar).isNotNull(),
                () -> assertThat(Objects.equals(movedCar.getName(), car.getName())).isTrue()
        );
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    void 해당_Car객체가_레이싱을_이겼는지_판단한다(Car car, int maxPosition, boolean expected) {
        // when
        boolean result = car.isWin(maxPosition);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                arguments(racingcar.domain.Car.createCar("a"), 0, true),
                arguments(racingcar.domain.Car.createCar("b"), 1, false)
        );
    }

    @Test
    void Car객체의_name을_반환한다(){
        // given
        String carName = "myCar";
        Car car = Car.createCar(carName);

        // when
        String name = car.getName();

        // then
        assertThat(name).isEqualTo(carName);
    }

    @Test
    void Car객체의_position을_반환한다(){
        // given
        Car car = Car.createCar("myCar");

        // when
        int position = car.getPosition();

        // then
        assertThat(position).isEqualTo(0);
    }
}