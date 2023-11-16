package christmas.service;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.exception.InvalidOrdersException;
import christmas.dto.OrderedMenusDto;
import christmas.dto.TotalAmountWithNoDiscountDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdersService {
    public Orders createOrders(Map<String, Integer> orders) throws InvalidOrdersException {
        return Orders.from(makeOrders(orders));
    }

    private List<Order> makeOrders(Map<String, Integer> orders) throws InvalidOrdersException {
        return orders.entrySet().stream()
                .map(order -> createOrder(order.getKey(), order.getValue()))
                .toList();
    }

    private Order createOrder(final String menuName, final int menuCount) throws InvalidOrdersException {
        return Order.of(menuName, menuCount);
    }

    public OrderedMenusDto createOrdersHistoryDto(Orders orders) {
        return OrderedMenusDto.from(makeOrdersHistory(orders));
    }

    private Map<String, Integer> makeOrdersHistory(Orders orders) {
        return orders.getOrders().stream()
                .collect(Collectors.toUnmodifiableMap(
                        Order::getMenuName,
                        Order::getMenuCount
                ));
    }

    public TotalAmountWithNoDiscountDto createTotalAmountWithNoDiscountDto(Orders orders) {
        return TotalAmountWithNoDiscountDto.from(orders.calculateTotalAmountWithNoDiscount());
    }
}