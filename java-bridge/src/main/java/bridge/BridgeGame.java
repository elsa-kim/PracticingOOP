package bridge;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private final TryCount tryCount;

    public BridgeGame(Bridge bridge, TryCount tryCount) {
        this.bridge = bridge;
        this.tryCount = tryCount;
    }

    public static BridgeGame initialize(int size) {
        return new BridgeGame(Bridge.generate(size), new TryCount(1));
    }


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(Direction direction, int index) {
        return this.bridge.isCorrect(direction, index);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}
