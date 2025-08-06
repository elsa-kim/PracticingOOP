package bridge;

import java.util.List;

public class Bridge {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;
    private static final String ERROR_INVALID_LENGTH = "다리 길이는 %d 이상 %d 이하로 설정해야합니다.";

    private final List<String> bridge;

    private Bridge(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        validate(size);
        this.bridge = bridgeMaker.makeBridge(size);
    }

    public static Bridge generate(int size) {
        return new Bridge(size);
    }

    private void validate(int size) {
        if (size < MIN_LENGTH || size > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_LENGTH, MIN_LENGTH, MAX_LENGTH));
        }
    }
}
