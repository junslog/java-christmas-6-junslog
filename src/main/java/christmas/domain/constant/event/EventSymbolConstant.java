package christmas.domain.constant.event;

public enum EventSymbolConstant {
    NO_BADGE_SYMBOL("");
    private final String symbol;

    EventSymbolConstant(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}