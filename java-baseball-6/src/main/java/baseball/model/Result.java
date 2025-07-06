package baseball.model;

import java.util.Objects;

public class Result {
    private static final int WINNING_STRIKE_COUNT = 3;

    private final int ball;
    private final int strike;

    public Result(int ball, int strike) {
        this.ball = ball;
        this.strike = strike;
    }

    public int getBall() {
        return this.ball;
    }

    public int getStrike() {
        return this.strike;
    }

    public boolean isThreeStrikes() {
        return this.strike == WINNING_STRIKE_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return ball == result.ball && strike == result.strike;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball, strike);
    }
}
