package racingcar;

import racingcar.model.Cars;
import racingcar.model.Racing;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        Cars cars = settingCars();
        Racing racing = settingRacing();
        outputView.printResultMessage();
        while (!racing.isFinish()) {
            cars = cars.changeCarsPosition();
            racing = racing.nextTurn();
            outputView.printMessage(cars.getProcess());
        }
        outputView.printMessage(cars.getResult());
    }


    private Racing settingRacing() {
        outputView.requestRaceCountMessage();
        return Racing.fromInput(inputView.getInput());
    }

    private Cars settingCars() {
        outputView.requestCarNamesMessage();
        return Cars.generateCars(inputView.getInput());
    }
}
