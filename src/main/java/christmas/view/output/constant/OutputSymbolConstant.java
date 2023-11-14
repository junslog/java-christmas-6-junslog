package christmas.view.output.constant;

public enum OutputSymbolConstant {
    NEW_LINE(System.lineSeparator()),
    ORDERED_MENUS("<주문 메뉴>"),
    TOTAL_AMOUNT_WITH_NO_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFITS_DETAILS("<혜택 내역>"),
    TOTAL_BENEFITED_AMOUNT("<총혜택 금액>"),
    NO_GIFT("없음"),
    NO_BENEFITS("없음");
    private final String symbol;

    OutputSymbolConstant(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}