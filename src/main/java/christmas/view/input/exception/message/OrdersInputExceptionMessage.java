package christmas.view.input.exception.message;

import static christmas.global.ApplicationConstant.ERROR_PREFIX;

public enum OrdersInputExceptionMessage {
    INVALID_ORDER_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    EMPTY_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_NUMERIC_TYPE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_POSITIVE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATED_MENUS("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    OrdersInputExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}