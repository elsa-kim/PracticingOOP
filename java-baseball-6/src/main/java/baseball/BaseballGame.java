package baseball;

import baseball.model.TargetNumber;
import baseball.model.GameState;
import baseball.model.Numbers;
import baseball.model.Result;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.startMessage();
        do {
            TargetNumber targetNumber = new TargetNumber();
            playGame(targetNumber);
        } while (isContinue());
    }

    private void playGame(TargetNumber targetNumber) {
        Result result = targetNumber.compareWith(getUserNumber());
        outputView.resultMessage(result.getBall(), result.getStrike());
        if (!result.isThreeStrikes()) {
            playGame(targetNumber);
        }
    }

    private boolean isContinue() {
        return checkRestart() == GameState.CONTINUE;
    }

    private GameState checkRestart() {
        outputView.requestContinueOrExitMessage();
        return inputView.getGameState();
    }

    private Numbers getUserNumber() {
        outputView.requestUserNumberMessage();
        return inputView.getUserInput();
    }

}
