package christmas.domain.exception;

public class InvalidOrdersException extends IllegalArgumentException {
    private InvalidOrdersException(final String invalidMenuExceptionMessage) {
        super(invalidMenuExceptionMessage);
    }

    public static InvalidOrdersException of(final String invalidMenuExceptionMessage) {
        return new InvalidOrdersException(invalidMenuExceptionMessage);
    }
}