package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class InputView {
    private static final String ERROR_INVALID_INPUT = "빈 값을 포함한 입력은 허용되지 않습니다.";

    public String readInput() {
        return Console.readLine();
    }

    public String readNames() {
        String input = readInput();
        if (hasBlankValue(input)) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT);
        }

        return input;
    }

    private static boolean hasBlankValue(String input) {
        return Arrays.stream(input.split(",", -1)).anyMatch(String::isBlank);
    }
}
