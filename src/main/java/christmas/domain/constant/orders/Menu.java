package christmas.domain.constant.orders;

import static christmas.domain.constant.orders.FoodType.APPETIZER;
import static christmas.domain.constant.orders.FoodType.BEVERAGE;
import static christmas.domain.constant.orders.FoodType.DESSERT;
import static christmas.domain.constant.orders.FoodType.MAIN;
import static christmas.domain.exception.message.InvalidOrdersExceptionMessage.NOT_EXISTING_MENU;

import christmas.domain.exception.InvalidOrdersException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),

    T_BONE_STAKE("티본스테이크", 55_000, MAIN),
    BARBECUE_RIB("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),

    ZERO_COKE("제로콜라", 3_000, BEVERAGE),
    RED_WINE("레드와인", 60_000, BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, BEVERAGE);

    private final String name;
    private final int price;
    private final FoodType foodType;

    Menu(final String name, final int price, final FoodType foodType) {
        this.name = name;
        this.price = price;
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isWeekDayPromotionApplicable() {
        return foodType.isWeekdayPromotionApplicable();
    }

    public boolean isWeekendPromotionApplicable() {
        return foodType.isWeekendPromotionApplicable();
    }

    public static Menu searchByName(final String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> InvalidOrdersException.of(NOT_EXISTING_MENU.getMessage()));
    }
}