package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public enum MenuCategory {
    한식, 일식, 중식, 아시안, 양식;

    public static MenuCategory pickOne() {
        return MenuCategory.values()[pickRandomNumber()];
    }

    private static int pickRandomNumber() {
        return Randoms.pickNumberInRange(0, MenuCategory.values().length - 1);
    }
}
