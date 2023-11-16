package christmas.view.output.constant;

import static christmas.global.ApplicationConstant.CURRENT_PROMOTION_MONTH;

public enum OutputMessageConstant {
    GREETING("안녕하세요! 우테코 식당 " + CURRENT_PROMOTION_MONTH + "월 이벤트 플래너입니다."),
    INSERT_RESERVATION_DAY(CURRENT_PROMOTION_MONTH + "월 중 식당 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INSERT_ORDERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String message;

    OutputMessageConstant(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}