package christmas.view.input.validator;

import static christmas.view.input.constant.InputNumberConstant.MENU_COUNT_INDEX;
import static christmas.view.input.constant.InputNumberConstant.MENU_NAME_INDEX;
import static christmas.view.input.constant.InputNumberConstant.ORDER_MAX_INPUT_LENGTH;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_DELIMITER;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.EMPTY_MENU_COUNT;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.INVALID_ORDER_FORMAT;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import java.util.Arrays;
import java.util.List;

public class OrderInputValidator extends BasicValidator {
    private final MenuNameInputValidator menuNameInputValidator;
    private final MenuCountInputValidator menuCountInputValidator;

    public OrderInputValidator() {
        this.menuNameInputValidator = new MenuNameInputValidator();
        this.menuCountInputValidator = new MenuCountInputValidator();
    }

    public void validate(final String orderInput) throws BasicInputException, OrdersInputException {
        super.validate(orderInput, ORDER_SYMBOL.getSymbol(), ORDER_MAX_INPUT_LENGTH.getValue());
        validateContainingDelimiter(orderInput);
        validateNotEndsWithDelimiter(orderInput);
        validateMenu(splitToMenuInput(orderInput));
    }

    private void validateContainingDelimiter(final String orderInput) {
        if (!containsDelimiter(orderInput)) {
            throw OrdersInputException.of(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private boolean containsDelimiter(final String orderInput) {
        return orderInput.contains(ORDER_DELIMITER.getSymbol());
    }

    private void validateNotEndsWithDelimiter(final String orderInput) {
        if (endsWithDelimiter(orderInput)) {
            throw OrdersInputException.of(EMPTY_MENU_COUNT.getMessage());
        }
    }

    private boolean endsWithDelimiter(final String orderInput) {
        return orderInput.endsWith(ORDER_DELIMITER.getSymbol());
    }

    private List<String> splitToMenuInput(final String orderInput) {
        return Arrays.stream(orderInput.split(ORDER_DELIMITER.getSymbol()))
                .toList();
    }

    private void validateMenu(List<String> menuInput) throws BasicInputException, OrdersInputException {
        menuNameInputValidator.validate(menuInput.get(MENU_NAME_INDEX.getValue()));
        menuCountInputValidator.validate(menuInput.get(MENU_COUNT_INDEX.getValue()));
    }
}