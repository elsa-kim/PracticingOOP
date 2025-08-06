package bridge;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeGame game = BridgeGame.initialize(inputView.readBridgeSize());


    }
}
