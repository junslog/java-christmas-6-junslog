package christmas.dto;

public class EstimatedAmountWithDiscountDto {
    private final int amount;

    public EstimatedAmountWithDiscountDto(final int amount) {
        this.amount = amount;
    }

    public static EstimatedAmountWithDiscountDto from(final int amount) {
        return new EstimatedAmountWithDiscountDto(amount);
    }

    public int getAmount() {
        return amount;
    }
}