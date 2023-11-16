package christmas.view.input.constant;

public enum InputNumberConstant {
    APPLICATION_MAX_INPUT_LENGTH(2000),
    ORDERS_MAX_INPUT_LENGTH(1000),
    ORDER_MAX_INPUT_LENGTH(33),
    MENU_NAME_MAX_INPUT_LENGTH(30),
    MENU_COUNT_MAX_INPUT_LENGTH(2),
    DAY_MAX_INPUT_LENGTH(2),
    MENU_NAME_INDEX(0),
    MENU_COUNT_INDEX(1),
    POSITIVE_BOUNDARY_VALUE(0);

    private final int value;

    InputNumberConstant(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}