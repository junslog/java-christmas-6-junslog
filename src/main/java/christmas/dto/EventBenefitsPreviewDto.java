package christmas.dto;

public class EventBenefitsPreviewDto {
    private final int day;

    public EventBenefitsPreviewDto(final int day) {
        this.day = day;
    }

    public static EventBenefitsPreviewDto from(final int day) {
        return new EventBenefitsPreviewDto(day);
    }

    public int getDay() {
        return day;
    }
}