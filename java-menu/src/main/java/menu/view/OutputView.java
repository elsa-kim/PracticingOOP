package menu.view;

import java.util.List;
import menu.dto.response.CoachResponse;
import menu.dto.response.RecommendResponse;

public class OutputView {
    public void requireCoachNameMessage() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void requestCannotEatFoodsMessage(String name) {
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", name);
    }

    public void printResult(RecommendResponse recommend) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printCategory(recommend.getMenuCategories());
        printMenu(recommend.getCoaches());
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    public void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private static void printMenu(List<CoachResponse> coaches) {
        for (CoachResponse coach : coaches) {
            System.out.printf("[ %s", coach.getCoachName());
            for (String menu : coach.getRecommendMenus()) {
                System.out.printf(" | %s", menu);
            }
            System.out.println(" ]");
        }
    }

    private static void printCategory(List<String> menuCategories) {
        System.out.print("[ 카테고리 ");
        for (String category : menuCategories) {
            System.out.printf("| %s ", category);
        }
        System.out.println("]");
    }
}