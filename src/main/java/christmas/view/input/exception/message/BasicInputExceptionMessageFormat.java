package christmas.view.input.exception.message;

import static christmas.global.ApplicationConstant.ERROR_PREFIX;

public enum BasicInputExceptionMessageFormat {
    TOO_LONG_WITH_BLANKS_FORMAT("유효하지 않은 %s입니다. 다시 입력해 주세요."),
    EMPTY_FORMAT("유효하지 않은 %s입니다. 다시 입력해 주세요."),
    TOO_LONG_FORMAT("유효하지 않은 %s입니다. 다시 입력해 주세요.");

    private final String format;

    BasicInputExceptionMessageFormat(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return ERROR_PREFIX + format;
    }
}