package bridge;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        outputView.printStartMessage();
        int size = getSize();
        BridgeGame game = BridgeGame.initialize(size);
        List<String> userChoice =new ArrayList<>();
        for(int i=0; i<size; i++) {
            userChoice.add(inputView.readMoving());
            
        }

    }

    private static int getSize() {
        outputView.printRequestBridgeSize();
        try {
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return getSize();
        }
    }
}
