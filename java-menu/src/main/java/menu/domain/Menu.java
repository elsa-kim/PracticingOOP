package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static menu.domain.MenuCategory.ASIAN;
import static menu.domain.MenuCategory.CHINESE;
import static menu.domain.MenuCategory.JAPANESE;
import static menu.domain.MenuCategory.KOREAN;
import static menu.domain.MenuCategory.WESTERN;

public enum Menu {
    GUDON("규동", JAPANESE),
    UDON("우동", JAPANESE),
    MISO_SOUP("미소시루", JAPANESE),
    SUSHI("스시", JAPANESE),
    KATSUDON("가츠동", JAPANESE),
    ONIGIRI("오니기리", JAPANESE),
    HAYASHI_RICE("하이라이스", JAPANESE),
    RAMEN("라멘", JAPANESE),
    OKONOMIYAKI("오코노미야끼", JAPANESE),

    GIMBAB("김밥", KOREAN),
    KIMCHI_STEW("김치찌개", KOREAN),
    SSAMBAP("쌈밥", KOREAN),
    DOENJANG_STEW("된장찌개", KOREAN),
    BIBIMBAP("비빔밥", KOREAN),
    KALGUKSU("칼국수", KOREAN),
    BULGOGI("불고기", KOREAN),
    TTEOKBOKKI("떡볶이", KOREAN),
    JEYUK_BOKKEUM("제육볶음", KOREAN),

    KANPUNGGI("깐풍기", CHINESE),
    FRIED_NOODLES("볶음면", CHINESE),
    DONGPAYUK("동파육", CHINESE),
    JJAJANGMYEON("짜장면", CHINESE),
    JJAMPPONG("짬뽕", CHINESE),
    MAPO_TOFU("마파두부", CHINESE),
    TANGSUYUK("탕수육", CHINESE),
    TOMATO_EGG_STIR_FRY("토마토 달걀볶음", CHINESE),
    GOCHU_JAPCHAE("고추잡채", CHINESE),

    PAD_THAI("팟타이", ASIAN),
    KHAO_PAD("카오 팟", ASIAN),
    NASI_GORENG("나시고렝", ASIAN),
    PINEAPPLE_FRIED_RICE("파인애플 볶음밥", ASIAN),
    RICE_NOODLES("쌀국수", ASIAN),
    TOM_YUM_GOONG("똠얌꿍", ASIAN),
    BANH_MI("반미", ASIAN),
    VIETNAMESE_SPRING_ROLL("월남쌈", ASIAN),
    BUN_CHA("분짜", ASIAN),

    LASAGNA("라자냐", WESTERN),
    GRATIN("그라탱", WESTERN),
    GNOCCHI("뇨끼", WESTERN),
    QUICHE("끼슈", WESTERN),
    FRENCH_TOAST("프렌치 토스트", WESTERN),
    BAGUETTE("바게트", WESTERN),
    SPAGHETTI("스파게티", WESTERN),
    PIZZA("피자", WESTERN),
    PANINI("파니니", WESTERN);

    private static final String ERROR_INVALID_MENU = "존재하지 않는 메뉴입니다.";

    private final String label;
    private final MenuCategory menuCategory;

    Menu(String label, MenuCategory menuCategory) {
        this.label = label;
        this.menuCategory = menuCategory;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getLabel().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INVALID_MENU));
    }

    public static Menu pickOne(MenuCategory pickedCategory, ExcludedMenus excludedMenus, List<Menu> pickedMenus) {
        List<String> menus = findMenusByCategory(pickedCategory, excludedMenus, pickedMenus);
        return Menu.from(Randoms.shuffle(menus).get(0));
    }

    private static List<String> findMenusByCategory(MenuCategory pickedCategory, ExcludedMenus excludedMenus, List<Menu> pickedMenus) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isSameCategory(pickedCategory))
                .filter(menu -> !excludedMenus.isContain(menu))
                .filter(menu -> !pickedMenus.contains(menu))
                .map(Menu::getLabel)
                .collect(Collectors.toList());
    }

    public String getLabel() {
        return this.label;
    }

    private boolean isSameCategory(MenuCategory category) {
        return this.menuCategory.equals(category);
    }
}
