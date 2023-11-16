package christmas.domain.constant.reservationday;

public enum ReservationDayConstant {
    DEFAULT_FIRST_DAY(1),
    DEFAULT_LAST_DAY(30),
    DECEMBER_FIRST_DAY(1),
    DECEMBER_LAST_DAY(31),
    DECEMBER_CHRISTMAS_DAY(25),
    WEEKDAY_LENGTH(7),
    FRIDAY_VALUE(1),
    SATURDAY_VALUE(2),
    SUNDAY_VALUE(3);

    private final int value;

    ReservationDayConstant(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}