package racingcar.view;

public class OutputView {

    public void requestCarNamesMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void requestRaceCountMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void printResultMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
