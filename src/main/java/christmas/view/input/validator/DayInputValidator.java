package christmas.view.input.validator;

import static christmas.view.input.constant.InputNumberConstant.DAY_MAX_INPUT_LENGTH;
import static christmas.view.input.constant.InputNumberConstant.POSITIVE_BOUNDARY_VALUE;
import static christmas.view.input.constant.InputSymbolConstant.DAY_SYMBOL;
import static christmas.view.input.exception.message.DayInputExceptionMessage.NOT_NUMERIC_TYPE;
import static christmas.view.input.exception.message.DayInputExceptionMessage.NOT_POSITIVE;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.DayInputException;

public class DayInputValidator extends BasicValidator implements PositiveIntegerCheckable {
    public void validate(final String dayInput) throws BasicInputException, DayInputException {
        super.validate(dayInput, DAY_SYMBOL.getSymbol(), DAY_MAX_INPUT_LENGTH.getValue());
        validateNumeric(dayInput);
        validatePositive(dayInput);
    }

    @Override
    public void validateNumeric(final String dayInput) {
        try {
            parseToInt(dayInput);
        } catch (NumberFormatException e) {
            throw DayInputException.of(NOT_NUMERIC_TYPE.getMessage());
        }
    }

    @Override
    public void validatePositive(final String dayInput) {
        int day = parseToInt(dayInput);
        if (day < POSITIVE_BOUNDARY_VALUE.getValue()) {
            throw DayInputException.of(NOT_POSITIVE.getMessage());
        }
    }

    private int parseToInt(final String dayInput) {
        return Integer.parseInt(dayInput);
    }
}