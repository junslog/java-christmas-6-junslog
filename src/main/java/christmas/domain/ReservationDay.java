package christmas.domain;

import static christmas.domain.constant.day.DayConstant.DECEMBER_CHRISTMAS_DAY;
import static christmas.domain.constant.day.DayConstant.DECEMBER_FIRST_DAY;
import static christmas.domain.constant.day.DayConstant.DECEMBER_LAST_DAY;
import static christmas.domain.constant.day.DayConstant.FRIDAY_VALUE;
import static christmas.domain.constant.day.DayConstant.SATURDAY_VALUE;
import static christmas.domain.constant.day.DayConstant.SUNDAY_VALUE;
import static christmas.domain.constant.day.DayConstant.WEEKDAY_LENGTH;

import christmas.domain.exception.InvalidDayException;

public class ReservationDay extends DayPerMonth {
    private final int day;

    private ReservationDay(final int day) {
        validate(day);
        this.day = day;
    }

    public static ReservationDay from(final int day) throws InvalidDayException {
        return new ReservationDay(day);
    }

    public boolean isChristmasPromotionApplicable() {
        return day >= DECEMBER_FIRST_DAY.getValue() && day <= DECEMBER_CHRISTMAS_DAY.getValue();
    }

    public boolean isWeekdayPromotionApplicable() {
        return !isWeekend();
    }

    public boolean isWeekendPromotionApplicable() {
        return isWeekend();
    }

    private boolean isWeekend() {
        return day % WEEKDAY_LENGTH.getValue() == FRIDAY_VALUE.getValue()
                || day % WEEKDAY_LENGTH.getValue() == SATURDAY_VALUE.getValue();
    }

    public boolean isSpecialPromotionApplicable() {
        return day % WEEKDAY_LENGTH.getValue() == SUNDAY_VALUE.getValue() || day == DECEMBER_CHRISTMAS_DAY.getValue();
    }

    public int getDay() {
        return day;
    }

    @Override
    protected boolean isInAppropriateRange(final int day) {
        return day >= DECEMBER_FIRST_DAY.getValue() && day <= DECEMBER_LAST_DAY.getValue();
    }
}