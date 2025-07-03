package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        Cars cars = settingCars();
        RacingGame racingGame = settingRacing();
        outputView.printResultMessage();
        while (!racingGame.isFinish()) {
            cars = cars.changeCarsPosition();
            racingGame = racingGame.nextTurn();
            outputView.printMessage(cars.getProcess());
        }
        outputView.printMessage(cars.getResult());
    }


    private RacingGame settingRacing() {
        outputView.requestRaceCountMessage();
        return RacingGame.fromInput(inputView.getInput());
    }

    private Cars settingCars() {
        outputView.requestCarNamesMessage();
        return Cars.generateCars(inputView.getInput());
    }
}
