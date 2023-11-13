package christmas.view.input.validator;

import static christmas.view.input.constant.InputNumberConstant.MENU_COUNT_INDEX;
import static christmas.view.input.constant.InputNumberConstant.MENU_NAME_INDEX;
import static christmas.view.input.constant.InputNumberConstant.ORDER_MAX_INPUT_LENGTH;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_DELIMITER;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.EMPTY_MENU_COUNT;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.INVALID_ORDER_FORMAT;

import christmas.view.input.exception.OrdersInputException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderInputValidator extends BasicValidator {
    private final MenuNameInputValidator menuNameInputValidator;
    private final MenuCountInputValidator menuCountInputValidator;

    public OrderInputValidator() {
        this.menuNameInputValidator = new MenuNameInputValidator();
        this.menuCountInputValidator = new MenuCountInputValidator();
    }

    public void validate(final String order) {
        super.validate(order, ORDER_SYMBOL.getSymbol(), ORDER_MAX_INPUT_LENGTH.getValue());
        validateContainingDelimiter(order);
        validateEndsWithDelimiter(order);
        validateMenu(splitToMenu(order));
    }

    private void validateContainingDelimiter(final String order) {
        if (!containsDelimiter(order)) {
            throw OrdersInputException.of(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private boolean containsDelimiter(final String order) {
        return order.contains(ORDER_DELIMITER.getSymbol());
    }

    private void validateEndsWithDelimiter(final String order) {
        if (endsWithDelimiter(order)) {
            throw OrdersInputException.of(EMPTY_MENU_COUNT.getMessage());
        }
    }

    private boolean endsWithDelimiter(final String order) {
        return order.endsWith(ORDER_DELIMITER.getSymbol());
    }

    private List<String> splitToMenu(final String order) {
        return Arrays.stream(order.split(ORDER_DELIMITER.getSymbol()))
                .collect(Collectors.toList());
    }

    private void validateMenu(List<String> menus) {
        menuNameInputValidator.validate(menus.get(MENU_NAME_INDEX.getValue()));
        menuCountInputValidator.validate(menus.get(MENU_COUNT_INDEX.getValue()));
    }
}