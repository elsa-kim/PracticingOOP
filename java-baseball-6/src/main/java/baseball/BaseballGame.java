package baseball;

import baseball.model.Game;
import baseball.model.GameState;
import static baseball.model.GameState.CONTINUE;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.startMessage();
        do {
            playGame();
        } while (isContinue());
    }

    private void playGame() {
        Game game = new Game();
        int userNumber = getUserNumber();
        while (!game.isSameNumber(userNumber)) {
            game.checkResult(userNumber);
            userNumber = getUserNumber();
        }
    }

    private boolean isContinue() {
        return checkRestart() == CONTINUE;
    }

    private GameState checkRestart() {
        outputView.requestContinueOrExitMessage();
        return inputView.getGameState();
    }

    private int getUserNumber() {
        outputView.requestUserNumberMessage();
        return Integer.parseInt(inputView.validate());
    }

}
