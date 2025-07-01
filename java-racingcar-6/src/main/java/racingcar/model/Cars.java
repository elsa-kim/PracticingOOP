package racingcar.model;

import java.util.Arrays;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars generateCars(String input) {
        List<Car> cars = Arrays.stream(input.split(",")).map(Car::createCar).toList();
        return new Cars(cars);
    }

}
