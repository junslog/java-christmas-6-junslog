package christmas.domain.constant.event;

public enum EventNumberConstant {
    EVENT_APPLICABLE_AMOUNT(10_000),
    GIFT_EVENT_APPLICABLE_AMOUNT(120_000),
    GIFT_CHAMPAGNE_COUNT(1),
    CHRISTMAS_PROMOTION_INCREASING_AMOUNT(100),
    NONE_PROMOTION_APPLIED_AMOUNT(0);

    private final int value;

    EventNumberConstant(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}