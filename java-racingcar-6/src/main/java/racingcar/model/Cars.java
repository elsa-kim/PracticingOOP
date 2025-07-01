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

    public String getProcess() {
        String process = "";
        for (Car car : cars) {
            process+=car.getCarInfo();
            process+="\n";
        }
        return process;
    }

    public String getResult() {
        String result = "최종 우승자 : ";
        int maxPosition = getMaxPosition();
        List<Car> winCars = cars.stream().filter(car->car.isWin(maxPosition)).toList();
        for (int i = 0; i < winCars.size(); i++) {
            if(i!=0) result += ", ";
            result += winCars.get(i).getCarName();
        }
        return result;
    }

    private int getMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.getMaxPosition(maxPosition);
        }
        return maxPosition;
    }
}
