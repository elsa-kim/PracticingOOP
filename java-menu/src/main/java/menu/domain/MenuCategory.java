package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public enum MenuCategory {
    일식, 한식, 중식, 아시안, 양식;

    public static MenuCategory pickOne() {
        return MenuCategory.values()[pickRandomNumber() - 1];
    }

    private static int pickRandomNumber() {
        return Randoms.pickNumberInRange(1, MenuCategory.values().length);
    }
}
