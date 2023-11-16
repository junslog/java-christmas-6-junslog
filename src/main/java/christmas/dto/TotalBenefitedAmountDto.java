package christmas.dto;

public class TotalBenefitedAmountDto {
    private final int amount;

    public TotalBenefitedAmountDto(final int amount) {
        this.amount = amount;
    }

    public static TotalBenefitedAmountDto from(final int amount) {
        return new TotalBenefitedAmountDto(amount);
    }

    public int getAmount() {
        return amount;
    }
}