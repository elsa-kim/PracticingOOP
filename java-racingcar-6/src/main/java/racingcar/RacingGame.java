package racingcar;

import racingcar.model.Cars;
import racingcar.model.Racing;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        outputView.requestCarNamesMessage();
        Cars.generateCars(inputView.getInput());
        outputView.requestRaceCountMessage();
        Racing.fromInput(inputView.getInput());
    }
}
