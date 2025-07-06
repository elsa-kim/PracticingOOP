package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class CarPosition {
    public static final String ERROR_INVALID_POSITION = "위치는 음수일 수 없습니다.";
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

    public CarPosition attemptMove() {
        if (canMove()) {
            return new CarPosition(this.position + 1);
        }
        return this;
    }

    private void validate(int position) {
        if (position < 0) {
            throw new IllegalArgumentException(ERROR_INVALID_POSITION);
        }
    }

    private static boolean canMove() {
        return Randoms.pickNumberInRange(0, 9) >= MOVE_THRESHOLD;
    }

    public int getPosition() {
        return this.position;
    }
}
