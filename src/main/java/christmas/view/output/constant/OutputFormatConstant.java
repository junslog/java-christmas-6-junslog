package christmas.view.output.constant;

import static christmas.global.ApplicationConstant.CURRENT_PROMOTION_MONTH;

public enum OutputFormatConstant {
    SHOW_RESULT_INTRO_FORMAT(CURRENT_PROMOTION_MONTH + "월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENUS_PRINT_FORMAT("%s %d개");
    
    private final String format;

    OutputFormatConstant(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}