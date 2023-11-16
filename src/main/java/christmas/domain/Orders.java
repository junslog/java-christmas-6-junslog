package christmas.domain;

import static christmas.domain.constant.event.EventNumberConstant.EVENT_APPLICABLE_AMOUNT;
import static christmas.domain.constant.event.EventNumberConstant.GIFT_EVENT_APPLICABLE_AMOUNT;
import static christmas.domain.constant.orders.OrdersConstant.MAX_MENU_COUNTS;
import static christmas.domain.exception.message.InvalidOrdersExceptionMessage.EXCEED_MENU_COUNTS_UPPER_LIMIT;
import static christmas.domain.exception.message.InvalidOrdersExceptionMessage.MENUS_ONLY_CONTAIN_BEVERAGE;

import christmas.domain.constant.orders.FoodType;
import christmas.domain.exception.InvalidOrdersException;
import java.util.Collections;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(List<Order> orders) {
        validate(orders);
        return new Orders(orders);
    }

    private static void validate(List<Order> orders) {
        validateSumOfMenuCountsNotExceedUpperLimit(orders);
        validateOrderedMenusNotOnlyContainBeverage(orders);
    }

    private static void validateSumOfMenuCountsNotExceedUpperLimit(List<Order> orders) {
        if (sumOfMenuCountsExceedUpperLimit(orders)) {
            throw InvalidOrdersException.of(EXCEED_MENU_COUNTS_UPPER_LIMIT.getMessage());
        }
    }

    private static boolean sumOfMenuCountsExceedUpperLimit(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getMenuCount)
                .sum() > MAX_MENU_COUNTS.getValue();
    }

    private static void validateOrderedMenusNotOnlyContainBeverage(List<Order> orders) {
        if (orderedMenusOnlyContainBeverage(orders)) {
            throw InvalidOrdersException.of(MENUS_ONLY_CONTAIN_BEVERAGE.getMessage());
        }
    }

    private static boolean orderedMenusOnlyContainBeverage(List<Order> orders) {
        return orders.stream()
                .allMatch(order -> order.getFoodType() == FoodType.BEVERAGE);
    }

    public int calculateTotalAmountWithNoDiscount() {
        return orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();
    }

    public boolean isEventApplicable() {
        return calculateTotalAmountWithNoDiscount() >= EVENT_APPLICABLE_AMOUNT.getValue();
    }

    public boolean isGiftEventApplicable() {
        return calculateTotalAmountWithNoDiscount() >= GIFT_EVENT_APPLICABLE_AMOUNT.getValue();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}