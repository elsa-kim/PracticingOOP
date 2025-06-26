package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Integer> numbers = new ArrayList<>();
    private final int randomNumber;

    public Game() {
        while (numbers.size() < 3) {
            int random = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        randomNumber = numbers.get(0) * 100 + numbers.get(1) * 10 + numbers.get(2);
    }

    public boolean isSameNumber(int userNumber) {
        return randomNumber == userNumber;
    }

    public void checkResult(int userNumber) {
        int ball = 0;
        int strike = 0;

        if (userNumber / 100 == randomNumber / 100) {
            strike++;
        } else if (numbers.contains(userNumber / 100)) {
            ball++;
        }

        if ((userNumber % 100) / 10 == (randomNumber % 100) / 10) {
            strike++;
        } else if (numbers.contains((userNumber % 100) / 10)) {
            ball++;
        }

        if (userNumber % 10 == randomNumber % 10) {
            strike++;
        } else if (numbers.contains(userNumber % 10)) {
            ball++;
        }

        Result result = new Result(ball, strike);
        result.printResult();
    }


}
