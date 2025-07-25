package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExcludedMenus {
    private static final String ERROR_EXCLUDED_MENU_COUNT = "못 먹는 메뉴는 최대 %d개 입력가능합니다.";
    private static final String ERROR_DUPLICATED_MENU = "메뉴를 중복 입력하였습니다.";

    private static final int MAX_EXCLUDED_COUNT = 2;

    private final List<Menu> excludedMenus;

    private ExcludedMenus(List<Menu> excludedMenus) {
        validate(excludedMenus);
        this.excludedMenus = excludedMenus;
    }

    public static ExcludedMenus from(String foodNames) {
        if (foodNames.isBlank()) {
            return new ExcludedMenus(new ArrayList<>());
        }

        List<Menu> excludedMenus = Arrays.stream(foodNames.split(",", -1))
                .map(Menu::from)
                .collect(Collectors.toList());

        return new ExcludedMenus(excludedMenus);
    }

    public boolean isContain(Menu menu) {
        return excludedMenus.contains(menu);
    }

    private void validate(List<Menu> excludedMenus) {
        validateCount(excludedMenus);
        validateDuplication(excludedMenus);
    }

    private void validateDuplication(List<Menu> excludedMenus) {
        if (isDuplicated(excludedMenus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_MENU);
        }
    }

    private boolean isDuplicated(List<Menu> excludedMenus) {
        return excludedMenus.stream().distinct().count() != excludedMenus.size();
    }

    private void validateCount(List<Menu> excludedMenus) {
        if (excludedMenus.size() > MAX_EXCLUDED_COUNT) {
            throw new IllegalArgumentException(String.format(ERROR_EXCLUDED_MENU_COUNT, MAX_EXCLUDED_COUNT));
        }
    }
}
