package racingcar.domain;

public class Car {
    private final CarName name;
    private final CarPosition position;

    public Car(CarName name, CarPosition position) {
        this.name = name;
        this.position = position;
    }

    public static Car createCar(String name) {
        return new Car(CarName.from(name), CarPosition.create());
    }

    public Car goOrStop() {
        return new Car(name, position.attemptMove());
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
}
