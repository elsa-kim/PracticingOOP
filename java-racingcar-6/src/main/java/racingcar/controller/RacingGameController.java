package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RaceCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        Cars cars = settingCars();
        RaceCount raceCount = settingRacing();
        renderGameProgress(raceCount, cars);
    }

    private void renderGameProgress(RaceCount raceCount, Cars cars) {
        outputView.printResultMessage();
        int tryCnt = 0;
        while (!raceCount.isFinish(tryCnt)) {
            cars = cars.goOrStop();
            outputView.printProcessMessage(cars.getCars());
            tryCnt++;
        }
        outputView.printFinalResultMessage(cars.getResult());
    }

    private RaceCount settingRacing() {
        outputView.requestRaceCountMessage();
        return RaceCount.fromInput(inputView.getInput());
    }

    private Cars settingCars() {
        outputView.requestCarNamesMessage();
        return Cars.generateCars(inputView.getInput());
    }
}
