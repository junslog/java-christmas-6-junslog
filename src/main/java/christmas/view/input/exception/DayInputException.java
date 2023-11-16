package christmas.view.input.exception;

public class DayInputException extends IllegalArgumentException {
    private DayInputException(final String dayInputExceptionMessage) {
        super(dayInputExceptionMessage);
    }

    public static DayInputException of(final String dayInputExceptionMessage) {
        return new DayInputException(dayInputExceptionMessage);
    }
}