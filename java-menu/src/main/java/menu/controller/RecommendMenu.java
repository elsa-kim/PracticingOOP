package menu.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.CoachName;
import menu.domain.CoachNames;
import menu.domain.ExcludedMenus;
import menu.domain.RecommendCategories;
import menu.dto.response.CoachResponse;
import menu.dto.response.RecommendResponse;
import menu.service.Recommender;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendMenu {
    private final InputView inputView;
    private final OutputView outputView;
    private final Recommender recommender;

    public RecommendMenu(InputView inputView, OutputView outputView, Recommender recommender) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.recommender = recommender;
    }

    public void recommend() {
        Map<CoachName, ExcludedMenus> excludedFoodsMap = settingInfo();
        RecommendCategories recommendCategories = RecommendCategories.generate();
        List<Coach> coaches = recommender.generate(recommendCategories, excludedFoodsMap);

        printResult(recommendCategories, coaches);
    }

    private void printResult(RecommendCategories recommendCategories, List<Coach> coaches) {
        List<CoachResponse> coachResponses = coaches.stream()
                .map(CoachResponse::from)
                .collect(Collectors.toList());

        RecommendResponse response = RecommendResponse.from(recommendCategories, coachResponses);
        outputView.printResult(response);
    }

    private Map<CoachName, ExcludedMenus> settingInfo() {
        outputView.printStartMessage();
        CoachNames coachNames = settingCoachesName();
        return settingCannotEatFoods(coachNames);
    }

    private Map<CoachName, ExcludedMenus> settingCannotEatFoods(CoachNames coaches) {
        Map<CoachName, ExcludedMenus> excludedFoodsMap = new LinkedHashMap<>();
        for (CoachName name : coaches.getCoachNames()) {
            excludedFoodsMap.put(name, requestCannotEatFoodsFor(name));
        }
        return excludedFoodsMap;
    }

    private ExcludedMenus requestCannotEatFoodsFor(CoachName name) {
        outputView.requestCannotEatFoodsMessage(name.getName());
        try {
            return ExcludedMenus.from(inputView.readInput());
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return requestCannotEatFoodsFor(name);
        }
    }

    private CoachNames settingCoachesName() {
        outputView.requireCoachNameMessage();
        try {
            return CoachNames.from(inputView.readNames());
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return settingCoachesName();
        }
    }
}
