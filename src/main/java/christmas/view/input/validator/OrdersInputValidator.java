package christmas.view.input.validator;

import static christmas.view.input.constant.InputNumberConstant.MENU_NAME_INDEX;
import static christmas.view.input.constant.InputNumberConstant.ORDERS_MAX_INPUT_LENGTH;
import static christmas.view.input.constant.InputSymbolConstant.ORDERS_DELIMITER;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_DELIMITER;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.DUPLICATED_MENUS;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdersInputValidator extends BasicValidator {
    public void preValidate(final String ordersInput) throws BasicInputException {
        super.validate(ordersInput, ORDER_SYMBOL.getSymbol(), ORDERS_MAX_INPUT_LENGTH.getValue());
    }

    public void postValidate(final String ordersInput) throws OrdersInputException {
        validateNotExistDuplicatedMenus(ordersInput);
    }

    private void validateNotExistDuplicatedMenus(final String ordersInput) {
        Set<String> uniqueMenuNames = makeUniqueMenuNames(ordersInput);
        if (haveDuplicatedMenus(ordersInput, uniqueMenuNames)) {
            throw OrdersInputException.of(DUPLICATED_MENUS.getMessage());
        }
    }

    private Set<String> makeUniqueMenuNames(final String ordersInput) {
        return Arrays.stream(ordersInput.split(ORDERS_DELIMITER.getSymbol()))
                .map(order -> order.split(ORDER_DELIMITER.getSymbol())[MENU_NAME_INDEX.getValue()])
                .collect(Collectors.toUnmodifiableSet());
    }

    private boolean haveDuplicatedMenus(final String ordersInput, Set<String> uniqueMenuNames) {
        return uniqueMenuNames.size() < ordersInput.split(ORDERS_DELIMITER.getSymbol()).length;
    }
}