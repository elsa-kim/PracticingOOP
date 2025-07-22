package menu;

import menu.controller.RecommendMenu;
import menu.service.Recommender;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Recommender recommender = new Recommender();

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        RecommendMenu recommendMenu = new RecommendMenu(inputView, outputView, recommender);
        recommendMenu.recommend();
    }
}
