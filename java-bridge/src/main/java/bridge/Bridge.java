package bridge;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bridge {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;
    private static final String ERROR_INVALID_LENGTH = "다리 길이는 %d부터 %d 사이의 숫자여야 합니다.";

    private final List<Direction> bridge;

    private Bridge(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        validate(size);

        this.bridge = bridgeMaker.makeBridge(size)
                .stream()
                .map(Direction::from)
                .collect(Collectors.toList());
    }

    public static Bridge generate(int size) {
        return new Bridge(size);
    }

    public boolean isCorrect(Direction direction, int index) {
        return Objects.equals(bridge.get(index), direction);
    }

    private void validate(int size) {
        if (size < MIN_LENGTH || size > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_LENGTH, MIN_LENGTH, MAX_LENGTH));
        }
    }
}
