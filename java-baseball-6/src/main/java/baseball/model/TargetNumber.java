package baseball.model;

public class TargetNumber {
    private final Numbers target;

    public TargetNumber() {
        this.target = Numbers.generateRandomNumbers();
    }

    public Result compareWith(Numbers userNumber) {
        return target.compareWith(userNumber);
    }
}
