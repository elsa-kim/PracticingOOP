package menu;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Recommend;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendMenu {
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();

    public void recommend() {
        outputView.printStartMessage();
        Coaches coaches = settingCoachesName();
        coaches = settingCannotEatFoods(coaches);
        Recommend recommend = Recommend.generate(coaches);
        outputView.printResult(recommend);
    }

    private Coaches settingCannotEatFoods(Coaches coaches) {
        List<Coach> updateCoaches = new ArrayList<>();
        for (Coach coach : coaches.getCoaches()) {
            updateCoaches.add(requestCannotEatFoodsFor(coach));
        }
        return Coaches.generate(updateCoaches);
    }

    private static Coach requestCannotEatFoodsFor(Coach coach) {
        outputView.requestCannotEatFoodsMessage(coach.getName());
        try {
            return coach.create(inputView.readInput());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestCannotEatFoodsFor(coach);
        }
    }

    private Coaches settingCoachesName() {
        outputView.requireCoachNameMessage();
        try {
            return Coaches.from(inputView.readInput());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return settingCoachesName();
        }
    }
}
