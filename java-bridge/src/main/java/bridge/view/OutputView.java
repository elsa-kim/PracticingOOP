package bridge.view;

import bridge.dto.response.ChoicesResponse;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printStartMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println();
    }

    public void printRequestBridgeSize(){
        System.out.println("다리의 길이를 입력해주세요.");
    }

    public void printRequestChoice(){
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public void printRetryMessage(){
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(ChoicesResponse response) {
        printPart(response.getSize(), response.getTop());
        printPart(response.getSize(), response.getBottom());
        System.out.println();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(ChoicesResponse response, boolean isSuccess, int tryCount) {
        System.out.println("최종 게임 결과");
        printMap(response);
        System.out.print("게임 성공 여부: ");
        if(isSuccess){
            System.out.println("성공");
        }else{
            System.out.println("실패");
        }
        System.out.println( "총 시도한 횟수: "+tryCount);
    }

    private static void printPart(int size, List<String> response) {
        System.out.print("[ ");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" | ");
            }
            System.out.print(response.get(i));
        }
        System.out.println(" ]");
    }
}
