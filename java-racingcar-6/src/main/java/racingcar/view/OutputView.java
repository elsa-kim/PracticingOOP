package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

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

    public void printProcessMessage(List<Car> cars) {
        String process = "";
        for (Car car : cars) {
            process += String.format("%s : %s\n", car.getName(), "-".repeat(car.getPosition()));
        }
        System.out.println(process);
    }

    public void printFinalResultMessage(List<Car> winCars) {
        String result = "최종 우승자 : ";
        for (int i = 0; i < winCars.size(); i++) {
            if (i != 0) {
                result += ", ";
            }
            result += winCars.get(i).getName();
        }
        System.out.println(result);
    }
}
