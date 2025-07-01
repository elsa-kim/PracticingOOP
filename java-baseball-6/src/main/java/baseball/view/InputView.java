package baseball.view;

import baseball.model.GameState;
import baseball.model.Numbers;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public Numbers getUserInput() {
        return Numbers.fromString(Console.readLine());
    }

    public GameState getGameState() {
        String input = Console.readLine();
        try {
            return GameState.checkState(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
