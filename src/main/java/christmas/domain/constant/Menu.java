package christmas.domain.constant;

import static christmas.domain.constant.FoodType.APPETIZER;
import static christmas.domain.constant.FoodType.BEVERAGE;
import static christmas.domain.constant.FoodType.DESSERT;
import static christmas.domain.constant.FoodType.MAIN;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),

    T_BONE_STAKE("티본스테이크", 55_000, MAIN),
    BARBECUE_RIB("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),

    CHOCHLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    ZERO_COKE("제로콜라", 3000, BEVERAGE),
    RED_WINE("레드와인", 60_000, BEVERAGE),
    CAHAMPAGNE("샴페인", 25_000, BEVERAGE);

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

    public boolean isWeekDayPromotionApplicable() {
        return foodType.isWeekdayPromotionApplicable();
    }

    public boolean isWeekendPromotionApplicable() {
        return foodType.isWeekendPromotionApplicable();
    }

    public static Menu findByName(final String name) {
        return Menu.valueOf(name);
    }
}