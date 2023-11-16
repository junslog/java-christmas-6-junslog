package christmas.domain.constant.event;

public enum Promotion {
    WEEKDAY_PROMOTION("평일 할인", 2_023),
    WEEKEND_PROMOTION("주말 할인", 2_023),
    SPECIAL_PROMOTION("특별 할인", 1_000),
    GIFT_PROMOTION("증정 이벤트", 25_000);

    private final String name;
    private final int benefitAmount;

    Promotion(final String name, final int benefitAmount) {
        this.name = name;
        this.benefitAmount = benefitAmount;
    }

    public String getName() {
        return name;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}