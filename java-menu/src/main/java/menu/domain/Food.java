package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.stream.Collectors;
import static menu.domain.MenuCategory.아시안;
import static menu.domain.MenuCategory.양식;
import static menu.domain.MenuCategory.일식;
import static menu.domain.MenuCategory.중식;
import static menu.domain.MenuCategory.한식;

public enum Food {
    규동(일식), 우동(일식), 미소시루(일식), 스시(일식), 가츠동(일식), 오니기리(일식), 하이라이스(일식), 라멘(일식), 오코노미야끼(일식),
    김밥(한식), 김치찌개(한식), 쌈밥(한식), 된장찌개(한식), 비빔밥(한식), 칼국수(한식), 불고기(한식), 떡볶이(한식), 제육볶음(한식),
    깐풍기(중식), 볶음면(중식), 동파육(중식), 짜장면(중식), 짬뽕(중식), 마파두부(중식), 탕수육(중식), 토마토_달걀볶음(중식), 고추잡채(중식),
    팟타이(아시안), 카오_팟(아시안), 나시고렝(아시안), 파인애플_볶음밥(아시안), 쌀국수(아시안), 똠얌꿍(아시안), 반미(아시안), 월남쌈(아시안), 분짜(아시안),
    라자냐(양식), 그라탱(양식), 뇨끼(양식), 끼슈(양식), 프렌치_토스트(양식), 바게트(양식), 스파게티(양식), 피자(양식), 파니니(양식);

    private final MenuCategory menuCategory;

    Food(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    private MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public static Food from(String input) {
        for (Food food : Food.values()) {
            if (input.replaceAll(" ", "_").equals(food.name())) {
                return food;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다.");
    }

    public static Food pickOne(MenuCategory pickedCategory) {
        return Food.valueOf(pickAtRandom(pickedCategory));
    }

    private static String pickAtRandom(MenuCategory pickedCategory) {
        return Randoms.shuffle(Arrays.stream(Food.values())
                        .filter(menu -> menu.getMenuCategory().equals(pickedCategory))
                        .map(Enum::toString)
                        .collect(Collectors.toList()))
                .get(0)
                .replaceAll(" ", "_");
    }
}
