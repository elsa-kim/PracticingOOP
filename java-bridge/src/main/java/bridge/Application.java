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
        runBridgeCrossing(size, game);

    }

    private static void runBridgeCrossing(int size, BridgeGame game) {
        List<Direction> userDirection =new ArrayList<>();
        for(int i = 0; i< size; i++) {
            userDirection.add(Direction.from(inputView.readMoving()));
            if(!game.move(userDirection.get(i), i)){
                outputView.printRetryMessage();
                processRetryOrQuit(size, game);
                break;
            };
        }
    }

    private static void processRetryOrQuit(int size, BridgeGame game) {
        GameCommand command = GameCommand.from(inputView.readGameCommand());
        if(command == GameCommand.RETRY){
            runBridgeCrossing(size, game);
        }
        if(command == GameCommand.QUIT){
            outputView.printResult();
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
