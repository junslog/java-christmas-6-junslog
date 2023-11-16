package christmas.dto;

import java.util.Collections;
import java.util.Map;

public class OrderedMenusDto {
    private final Map<String, Integer> orders;

    public OrderedMenusDto(Map<String, Integer> orders) {
        this.orders = orders;
    }

    public static OrderedMenusDto from(Map<String, Integer> orders) {
        return new OrderedMenusDto(orders);
    }

    public Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}