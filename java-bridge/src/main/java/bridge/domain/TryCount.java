package bridge.domain;

public class TryCount {
    private int count;

    public TryCount(int count) {
        this.count = count;
    }

    public int getTryCount() {
        return this.count;
    }

    public void increase() {
        this.count++;
    }
}
