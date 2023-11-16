package christmas.domain.exception;

public class InvalidReservationDayException extends IllegalArgumentException {
    private InvalidReservationDayException(final String invalidDayExceptionMessage) {
        super(invalidDayExceptionMessage);
    }

    public static InvalidReservationDayException of(final String invalidDayExceptionMessage) {
        return new InvalidReservationDayException(invalidDayExceptionMessage);
    }
}