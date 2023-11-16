package christmas.domain.constant.orders;

public enum FoodType {

    APPETIZER(false, false),
    MAIN(false, true),
    DESSERT(true, false),
    BEVERAGE(false, false);

    private final boolean isWeekdayPromotionApplicable;
    private final boolean isWeekendPromotionApplicable;

    FoodType(final boolean isWeekdayPromotionApplicable, final boolean isWeekendPromotionApplicable) {
        this.isWeekdayPromotionApplicable = isWeekdayPromotionApplicable;
        this.isWeekendPromotionApplicable = isWeekendPromotionApplicable;
    }

    public boolean isWeekdayPromotionApplicable() {
        return isWeekdayPromotionApplicable;
    }

    public boolean isWeekendPromotionApplicable() {
        return isWeekendPromotionApplicable;
    }
}