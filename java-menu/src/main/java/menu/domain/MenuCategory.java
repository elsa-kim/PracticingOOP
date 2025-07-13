package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public enum MenuCategory {
    JAPANESE("일식"),
    KOREAN("한식"),
    CHINESE("중식"),
    ASIAN("아시안"),
    WESTERN("양식");

    private String label;

    MenuCategory(String label) {
        this.label = label;
    }

    public static MenuCategory pickOne() {
        return MenuCategory.values()[pickRandomNumber() - 1];
    }

    public String getLabel() {
        return this.label;
    }

    private static int pickRandomNumber() {
        return Randoms.pickNumberInRange(1, MenuCategory.values().length);
    }
}
