package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public enum MenuCategory {
    한식, 일식, 중식, 아시안, 양식;

    public static MenuCategory pickOne() {
        return MenuCategory.values()[pickRandomNumber() - 1];
    }

    public static int pickRandomNumber() {
        System.out.println();
        return Randoms.pickNumberInRange(1, MenuCategory.values().length);
    }
}
