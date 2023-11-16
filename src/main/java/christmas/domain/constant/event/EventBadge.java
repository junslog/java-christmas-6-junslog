package christmas.domain.constant.event;

import static christmas.domain.constant.event.EventSymbolConstant.NO_BADGE_SYMBOL;

public enum EventBadge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumTotalBenefitedPrice;

    EventBadge(final String name, final int minimumTotalBenefitedPrice) {
        this.name = name;
        this.minimumTotalBenefitedPrice = minimumTotalBenefitedPrice;
    }

    public static String getBadgeNameByBenefitedPrice(final int totalBenefitedPrice) {
        if (isStar(totalBenefitedPrice)) {
            return STAR.name;
        }
        if (isTree(totalBenefitedPrice)) {
            return TREE.name;
        }
        if (isSanta(totalBenefitedPrice)) {
            return SANTA.name;
        }
        return NO_BADGE_SYMBOL.getSymbol();
    }

    private static boolean isStar(final int totalBenefitedPrice) {
        return totalBenefitedPrice >= STAR.minimumTotalBenefitedPrice
                && totalBenefitedPrice < TREE.minimumTotalBenefitedPrice;
    }

    private static boolean isTree(final int totalBenefitedPrice) {
        return totalBenefitedPrice >= TREE.minimumTotalBenefitedPrice
                && totalBenefitedPrice < SANTA.minimumTotalBenefitedPrice;
    }

    private static boolean isSanta(final int totalBenefitedPrice) {
        return totalBenefitedPrice >= SANTA.minimumTotalBenefitedPrice;
    }
}