package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

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

    public Car changeCarPosition() {
        return new Car(name, getNewPosition(position));
    }

    private int getNewPosition(int position) {
        if (isGo()) {
            return position + 1;
        }
        return position;
    }

    private static boolean isGo() {
        return Randoms.pickNumberInRange(0, 9) >= 4;
    }

    private static void validateNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5글자를 넘길 수 없습니다.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 1글자 이상 작성해야 합니다.");
        }
        if(name.replaceAll(" ", "").isEmpty()) {
            throw new IllegalArgumentException("자동차 이름에 문자를 포함해야합니다.");
        }
    }


    public String getCarInfo() {
        return String.format("%s : %s", name, "-".repeat(position));
    }

    public int getMaxPosition(int maxPosition) {
        return Math.max(maxPosition, position);
    }

    public boolean isWin(int maxPosition) {
        return position == maxPosition;
    }

    public String getCarName() {
        return name;
    }
}
