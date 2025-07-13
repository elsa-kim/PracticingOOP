package menu.view;

import camp.nextstep.edu.missionutils.Console;
import menu.dto.request.CoachNamesRequest;
import menu.dto.request.ExcludedFoodsRequest;

public class InputView {
    public CoachNamesRequest readCoachesNameInput() {
        return CoachNamesRequest.from(readInput());
    }

    public ExcludedFoodsRequest readExcludedFoodsInput() {
        return ExcludedFoodsRequest.from(readInput());
    }

    private String readInput() {
        return Console.readLine();
    }
}
