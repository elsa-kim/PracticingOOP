package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String ERROR_INVALID_FORMAT = "숫자 형식이 아닙니다.";
    private static final String ERROR_INVALID_INPUT = "빈 값은 입력할 수 없습니다.";

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        try {
            return Integer.parseInt(readInput());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String input = readInput();
        validateBlank(input);
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        String input = readInput();
        validateBlank(input);
        return input;
    }

    private void validateBlank(String input) {
        if(input.isBlank()){
            throw new IllegalArgumentException(ERROR_INVALID_INPUT);
        }
    }

    private String readInput() {
        return Console.readLine().trim();
    }
}
