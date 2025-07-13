package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Objects;

public class Car {
    private final CarName name;
    private final CarPosition position;

    private Car(CarName name, CarPosition position) {
        this.name = name;
        this.position = position;
    }

    public static Car createCar(String name) {
        return new Car(CarName.from(name), CarPosition.create());
    }

    public Car goOrStop() {
        int moveConditionValue = Randoms.pickNumberInRange(0, 9);
        return new Car(name, position.attemptMove(moveConditionValue));
    }

    public boolean isWin(int maxPosition) {
        return position.getPosition() == maxPosition;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(position, car.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
