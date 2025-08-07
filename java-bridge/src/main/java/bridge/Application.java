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
        List<String[]> result = new ArrayList<>();
        for(int i = 0; i< size; i++) {
            handleUserDirectionInput(userDirection);
            if(!game.move(userDirection.get(i), i)){
                result.add(new String[]{userDirection.get(i).toString(), "X"});
                outputView.printMap(ChoicesResponse.generate(result));
                handleRetry(size, game, result);
                return;
            }else{
                result.add(new String[]{userDirection.get(i).toString(), "O"});
                outputView.printMap(ChoicesResponse.generate(result));
            }

        }
        outputView.printResult(ChoicesResponse.generate(result), true, game.getTryCount());
    }

    private static void handleRetry(int size, BridgeGame game, List<String[]> result) {
        outputView.printRetryMessage();
        try {
            processRetryOrQuit(size, game, ChoicesResponse.generate(result));
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            handleRetry(size, game, result);
        }
    }

    private static void handleUserDirectionInput(List<Direction> userDirection) {
        outputView.printRequestChoice();
        try {
            userDirection.add(Direction.from(inputView.readMoving()));
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            handleUserDirectionInput(userDirection);
        }
    }

    private static void processRetryOrQuit(int size, BridgeGame game, ChoicesResponse choicesResponse) {
        GameCommand command = GameCommand.from(inputView.readGameCommand());
        if(command == GameCommand.RETRY){
            runBridgeCrossing(size, game.retry());
        }
        if(command == GameCommand.QUIT){
            outputView.printResult(choicesResponse, false, game.getTryCount());
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
