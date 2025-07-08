package racingcar.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cars {
    private static final String ERROR_DUPLICATE_CAR_NAME = "중복된 이름을 사용할 수 없습니다.";

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        validateDuplication(cars);
        this.cars = cars;
    }

    public static Cars generateCars(String input) {
        List<Car> cars = Arrays.stream(input.split(",", -1)).map(Car::createCar).toList();
        return new Cars(cars);
    }

    private void validateDuplication(List<Car> cars) {
        if (isDuplication(cars)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_CAR_NAME);
        }
    }

    private boolean isDuplication(List<Car> cars) {
        return cars.stream().distinct().count() != cars.size();
    }

    public Cars goOrStop() {
        return new Cars(cars.stream().map(Car::goOrStop).toList());
    }

    public List<Car> getResult() {
        int maxPosition = getMaxPosition();
        return cars.stream().filter(car -> car.isWin(maxPosition)).toList();
    }

    private int getMaxPosition() {
        List<Integer> positions = cars.stream()
                .map(Car::getPosition)
                .toList();

        return Collections.max(positions);
//        int maxPosition = cars.get(0).getPosition();
//        for (Car car : cars) {
//            maxPosition = Math.max(car.getPosition(), maxPosition);
//        }
//        return maxPosition;
    }

    public List<Car> getCars() {
//        Collections.unmodifiableList(cars);
//        new ArrayList<>(cars);
//        Arrays.asList(cars.toArray());
//        List.of(cars.toArray());
        return List.copyOf(cars);
    }
}
