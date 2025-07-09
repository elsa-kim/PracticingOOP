package menu;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.MenuCategory;
import menu.domain.Recommend;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendMenu {
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();

    public void recommend() {
        Coaches coaches = settingCoachesName();
        coaches = settingCannotEatFoods(coaches);
        Recommend recommend = Recommend.generate(coaches);
    }

    private Coaches settingCannotEatFoods(Coaches coaches) {
        List<Coach> updateCoaches = new ArrayList<>();
        for (Coach coach : coaches.getCoaches()) {
            outputView.requireCannotEatFoodsMessage(coach.getName());
            updateCoaches.add(coach.create(inputView.readInput()));
        }
        return Coaches.generate(updateCoaches);
    }

    private Coaches settingCoachesName() {
        outputView.requireCoachNameMessage();
        return Coaches.from(inputView.readInput());
    }
}
