package christmas.dto;

public class TotalAmountWithNoDiscountDto {
    private final int amount;

    public TotalAmountWithNoDiscountDto(final int amount) {
        this.amount = amount;
    }

    public static TotalAmountWithNoDiscountDto from(final int amount) {
        return new TotalAmountWithNoDiscountDto(amount);
    }

    public int getAmount() {
        return amount;
    }
}