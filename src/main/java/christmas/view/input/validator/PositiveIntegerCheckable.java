package christmas.view.input.validator;

public interface PositiveIntegerCheckable {
    void validateNumeric(final String userInput);

    void validatePositive(final String userInput);
}