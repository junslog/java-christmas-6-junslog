package christmas.view.input.validator;

import static christmas.view.input.constant.InputNumberConstant.MENU_COUNT_MAX_INPUT_LENGTH;
import static christmas.view.input.constant.InputNumberConstant.POSITIVE_BOUNDARY_VALUE;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.NOT_NUMERIC_TYPE;
import static christmas.view.input.exception.message.OrdersInputExceptionMessage.NOT_POSITIVE;

import christmas.view.input.exception.OrdersInputException;

public class MenuCountInputValidator extends BasicValidator implements PositiveIntegerCheckable {
    public void validate(final String menuCountInput) throws OrdersInputException {
        super.validate(menuCountInput, ORDER_SYMBOL.getSymbol(), MENU_COUNT_MAX_INPUT_LENGTH.getValue());
        validateNumeric(menuCountInput);
        validatePositive(menuCountInput);
    }

    @Override
    public void validateNumeric(final String menuCountInput) {
        try {
            parseToInt(menuCountInput);
        } catch (NumberFormatException e) {
            throw OrdersInputException.of(NOT_NUMERIC_TYPE.getMessage());
        }
    }

    @Override
    public void validatePositive(final String menuCountInput) {
        int menuCount = parseToInt(menuCountInput);
        if (menuCount < POSITIVE_BOUNDARY_VALUE.getValue()) {
            throw OrdersInputException.of(NOT_POSITIVE.getMessage());
        }
    }

    private int parseToInt(final String menuCountInput) {
        return Integer.parseInt(menuCountInput);
    }
}