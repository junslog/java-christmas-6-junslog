package christmas.view.input.exception.message;

import static christmas.global.ApplicationConstant.ERROR_PREFIX;

public enum DayInputExceptionMessage {
    NOT_NUMERIC_TYPE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_POSITIVE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;

    DayInputExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}