package christmas.view.input.exception;

public class OrdersInputException extends IllegalArgumentException {
    private OrdersInputException(final String ordersInputExceptionMessage) {
        super(ordersInputExceptionMessage);
    }

    public static OrdersInputException of(final String ordersInputExceptionMessage) {
        return new OrdersInputException(ordersInputExceptionMessage);
    }
}