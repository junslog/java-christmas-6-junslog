package christmas.controller;

import christmas.domain.Orders;
import christmas.domain.exception.InvalidOrdersException;
import christmas.dto.OrderedMenusDto;
import christmas.dto.TotalAmountWithNoDiscountDto;
import christmas.service.OrdersService;
import christmas.view.input.InputView;
import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import christmas.view.output.OutputView;
import java.util.Map;

public class OrdersController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OrdersService ordersService;

    public OrdersController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        ordersService = new OrdersService();
    }

    public Orders insertOrders() {
        try {
            Map<String, Integer> orders = askToInsertOrders();
            return ordersService.createOrders(orders);
        } catch (BasicInputException | OrdersInputException | InvalidOrdersException e) {
            outputView.printErrorMessage(e.getMessage());
            return insertOrders();
        }
    }

    private Map<String, Integer> askToInsertOrders() throws BasicInputException, OrdersInputException {
        outputView.askToInsertOrders();
        return inputView.getOrders();
    }

    public OrderedMenusDto createOrderedMenusDto(Orders orders) {
        return ordersService.createOrdersHistoryDto(orders);
    }

    public TotalAmountWithNoDiscountDto createTotalAmountWithNoDiscountDto(Orders orders) {
        return ordersService.createTotalAmountWithNoDiscountDto(orders);
    }
}