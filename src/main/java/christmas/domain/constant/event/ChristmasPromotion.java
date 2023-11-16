package christmas.domain.constant.event;

import static christmas.domain.constant.event.EventNumberConstant.CHRISTMAS_PROMOTION_INCREASING_AMOUNT;

public enum ChristmasPromotion {
    CHRISTMAS_D_DAY_PROMOTION("크리스마스 디데이 할인", 900);

    private final String name;
    private final int startBenefitAmount;

    ChristmasPromotion(final String name, final int startBenefitAmount) {
        this.name = name;
        this.startBenefitAmount = startBenefitAmount;
    }

    public String getName() {
        return name;
    }

    public int getBenefitAmount(final int day) {
        return startBenefitAmount + day * CHRISTMAS_PROMOTION_INCREASING_AMOUNT.getValue();
    }
}