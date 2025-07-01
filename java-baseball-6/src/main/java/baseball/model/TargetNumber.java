package baseball.model;

public class TargetNumber {
    private final Numbers target;

    public TargetNumber() {
        this.target = Numbers.generateRandomNumbers();
    }

    public Result checkResult(Numbers userNumber) {
        return target.evaluateMatches(userNumber);
    }
}
