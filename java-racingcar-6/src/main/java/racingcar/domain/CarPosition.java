package racingcar.domain;

import java.util.Objects;

public class CarPosition {
    private static final String ERROR_INVALID_POSITION = "위치는 음수일 수 없습니다.";
    private static final int START_POSITION = 0;
    private static final int MOVE_THRESHOLD = 4;

    private final int position;

    private CarPosition(int position) {
        validate(position);
        this.position = position;
    }

    public static CarPosition create() {
        return new CarPosition(START_POSITION);
    }

    public CarPosition attemptMove(int moveConditionValue) {
        if (canMove(moveConditionValue)) {
            return new CarPosition(this.position + 1);
        }
        return this;
    }

    private void validate(int position) {
        if (position < 0) {
            throw new IllegalArgumentException(ERROR_INVALID_POSITION);
        }
    }

    private static boolean canMove(int moveConditionValue) {
        return moveConditionValue >= MOVE_THRESHOLD;
    }

    public int getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarPosition that = (CarPosition) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }
}
