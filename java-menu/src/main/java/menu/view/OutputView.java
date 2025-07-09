package menu.view;

import java.util.List;
import menu.domain.Coach;
import menu.domain.Food;
import menu.domain.MenuCategory;
import menu.domain.Recommend;

public class OutputView {
    public void requireCoachNameMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void requireCannotEatFoodsMessage(String name) {
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", name);
    }

    public void printResult(Recommend recommend) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printCategory(recommend.getMenuCategories());
        printMenu(recommend.getCoaches());
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    private static void printMenu(List<Coach> coaches) {
        for (Coach coach : coaches) {
            System.out.printf("[ %s", coach.getName());
            for (Food menu : coach.getRecommendFoods()) {
                System.out.printf(" | %s", menu.name().replaceAll("_", " "));
            }
            System.out.println(" ]");
        }
    }

    private static void printCategory(List<MenuCategory> menuCategories) {
        System.out.print("[ 카테고리 ");
        for (MenuCategory category : menuCategories) {
            System.out.printf("| %s ", category.name());
        }
        System.out.println("]");
    }
}
