package racingcar.model;

public class Car {
    private final String name;
    private final int position;

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static Car createCar(String name) {
        validateNameLength(name);
        return new Car(name, 0);
    }

    private static void validateNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5글자를 넘길 수 없습니다.");
        }
    }

}
