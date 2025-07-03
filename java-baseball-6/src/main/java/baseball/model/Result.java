package baseball.model;

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
}
