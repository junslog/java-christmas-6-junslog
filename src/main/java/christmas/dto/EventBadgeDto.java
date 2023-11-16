package christmas.dto;

public class EventBadgeDto {
    private final String badgeName;

    public EventBadgeDto(final String badgeName) {
        this.badgeName = badgeName;
    }

    public static EventBadgeDto from(final String badgeName) {
        return new EventBadgeDto(badgeName);
    }

    public String getBadgeName() {
        return badgeName;
    }

    public boolean isEmpty() {
        return badgeName.isEmpty();
    }
}