package baseball.view;

import baseball.model.GameState;
import static camp.nextstep.edu.missionutils.Console.readLine;
import java.util.regex.Pattern;

public class InputView {

    public String validate() {
        String input = readLine();
        if (!Pattern.matches("^[\\d]*$", input)) {
            throw new IllegalArgumentException("잘못된 값 입력");
        }
        if (input.length() != 3) {
            throw new IllegalArgumentException("잘못된 길이 입력");
        }
        if (input.length() != input.chars().distinct().count()) {
            throw new IllegalArgumentException("중복값 존재");
        }
        return input;
    }

    public GameState getGameState() {
        String input = readLine();
        try {
            return GameState.valueOf(input);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
