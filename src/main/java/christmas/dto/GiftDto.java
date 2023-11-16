package christmas.dto;

import java.util.Collections;
import java.util.Map;

public class GiftDto {
    private final Map<String, Integer> gift;

    private GiftDto(Map<String, Integer> gift) {
        this.gift = gift;
    }

    public static GiftDto from(Map<String, Integer> gift) {
        return new GiftDto(gift);
    }

    public Map<String, Integer> getGift() {
        return Collections.unmodifiableMap(gift);
    }

    public boolean isEmpty() {
        return gift.isEmpty();
    }
}