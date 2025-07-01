package racingcar.model;

import java.util.Arrays;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars generateCars(String input) {
        validateDuplication(input);
        List<Car> cars = Arrays.stream(input.split(",")).map(Car::createCar).toList();
        return new Cars(cars);
    }

    private static void validateDuplication(String input) {
        if (Arrays.stream(input.split(",")).distinct().count() != input.split(",").length) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    public Cars changeCarsPosition() {
        return new Cars(cars.stream().map(Car::changeCarPosition).toList());
    }
}
