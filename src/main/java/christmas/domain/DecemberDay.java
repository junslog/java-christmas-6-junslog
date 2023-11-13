package christmas.domain;

import static christmas.domain.constant.DayConstant.DECEMBER_FIRST_DAY;
import static christmas.domain.constant.DayConstant.DECEMBER_LAST_DAY;

public class DecemberDay extends DayPerMonth {
    private final boolean isChristmasPromotionApplicable;
    private final boolean isWeekend;
    private final boolean hasStar;

    private DecemberDay(final int day, final boolean isChristmasPromotionApplicable, final boolean isWeekend,
                        final boolean hasStar) {
        super(day);
        this.FIRST_DAY = DECEMBER_FIRST_DAY.getDay();
        this.LAST_DAY = DECEMBER_LAST_DAY.getDay();
        this.isChristmasPromotionApplicable = isChristmasPromotionApplicable;
        this.isWeekend = isWeekend;
        this.hasStar = hasStar;
    }

    public static DecemberDay from(final int day) {
        return new DecemberDay(day, judgeIsChristmasPromotionApplicable(day), judgeIsWeekend(day), judgeHasStar(day));
    }

    private static boolean judgeIsChristmasPromotionApplicable(final int day) {
        return true;
    }

    private static boolean judgeIsWeekend(final int day) {
        return true;
    }

    private static boolean judgeHasStar(final int day) {
        return true;
    }
}