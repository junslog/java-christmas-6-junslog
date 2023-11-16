package christmas.domain;

import static christmas.domain.constant.reservationday.ReservationDayConstant.DECEMBER_LAST_DAY;
import static christmas.domain.constant.reservationday.ReservationDayConstant.DEFAULT_FIRST_DAY;
import static christmas.domain.exception.message.InvalidReservationDayExceptionMessage.NOT_IN_APPROPRIATE_RANGE;

import christmas.domain.exception.InvalidReservationDayException;

public abstract class DayPerMonth {
    public DayPerMonth() {
    }

    protected void validate(final int day) {
        if (!isInAppropriateRange(day)) {
            throw InvalidReservationDayException.of(NOT_IN_APPROPRIATE_RANGE.getMessage());
        }
    }

    protected boolean isInAppropriateRange(final int day) {
        return day >= DEFAULT_FIRST_DAY.getValue() && day <= DECEMBER_LAST_DAY.getValue();
    }
}