package christmas.view.input.validator;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;

public abstract class BasicValidator {

    protected void validate(final String userInput, final String inputType, final int maxLength)
            throws BasicInputException {
        validateNotEmpty(userInput, inputType);
        validateLengthUnderMaxLength(userInput, inputType, maxLength);
    }

    protected void validateNotEmpty(final String userInput, final String inputType) {
        if (userInput.isEmpty()) {
            throw BasicInputException.of(
                    String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(), inputType));
        }
    }

    protected void validateLengthUnderMaxLength(final String userInput, final String inputType, final int maxLength) {
        if (userInput.length() > maxLength) {
            throw BasicInputException.of(
                    String.format(BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(), inputType));
        }
    }
}