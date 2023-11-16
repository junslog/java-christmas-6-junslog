package christmas.view.input.exception;

public class BasicInputException extends IllegalArgumentException {
    private BasicInputException(final String invalidDayExceptionMessage) {
        super(invalidDayExceptionMessage);
    }

    public static BasicInputException of(final String basicInputExceptionMessage) {
        return new BasicInputException(basicInputExceptionMessage);
    }
}