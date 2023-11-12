package christmas.model.type;

import java.util.List;

import static christmas.common.ErrorMessageType.*;
import static christmas.model.type.MenuType.*;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BBQ_LIP("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),
    ZERO_COKE("제로콜라", 3000, BEVERAGE),
    RED_WINE("레드와인", 60000, BEVERAGE),
    CHAMPAGNE("샴페인", 25000, BEVERAGE),
    ;

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu findByName(String name) {
        return List.of(Menu.values()).stream()
                .filter(m -> m.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage()));
    }

    public static boolean isBeverage(Menu menu) {
        return menu.menuType.equals(BEVERAGE);
    }

    public String getName() {
        return name;
    }

    public MenuType getMenuType() {
        return menuType;
    }
}
