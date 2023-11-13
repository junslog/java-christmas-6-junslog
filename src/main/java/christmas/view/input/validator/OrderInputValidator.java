package christmas.view.input.validator;

import static christmas.view.input.constant.InputConstant.ORDER_DELIMITER;
import static christmas.view.input.constant.InputConstant.ORDER_MAX_INPUT_LENGTH;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.INVALID_ORDER_FORMAT;

import christmas.view.input.exception.OrdersInputException;

public class OrderInputValidator extends BasicValidator {
    private final MenuNameInputValidator menuNameInputValidator;
    private final MenuCountInputValidator menuCountInputValidator;

    public OrderInputValidator() {
        this.menuNameInputValidator = new MenuNameInputValidator();
        this.menuCountInputValidator = new MenuCountInputValidator();
    }

    public void validate(final String order) {
        super.validate(order, ORDER_MAX_INPUT_LENGTH);
        validateContainingDelimiter(order);
    }

    private void validateContainingDelimiter(final String order) {
        if (!containsDelimiter(order)) {
            throw OrdersInputException.of(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private boolean containsDelimiter(final String order) {
        return order.contains(ORDER_DELIMITER);
    }
}