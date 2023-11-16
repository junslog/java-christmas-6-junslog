package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrdersController;
import christmas.controller.ReservationDayController;
import christmas.domain.EventManager;
import christmas.domain.Orders;
import christmas.domain.ReservationDay;
import christmas.service.EventManagerService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class EventPlanner {
    private final OutputView outputView;
    private final ReservationDayController reservationDayController;
    private final OrdersController ordersController;
    private final EventManagerService eventManagerService;

    public EventPlanner(InputView inputView, OutputView outputView) {
        this.outputView = outputView;
        reservationDayController = new ReservationDayController(inputView, outputView);
        ordersController = new OrdersController(inputView, outputView);
        eventManagerService = new EventManagerService();
        outputView.printGreetingMessage();
    }

    public void execute() {
        ReservationDay reservationDay = reservationDayController.insertReservationDay();
        Orders orders = ordersController.insertOrders();
        EventManager eventManager = EventManager.of(reservationDay, orders);
        printResult(reservationDay, orders, eventManager);
        terminatePlanner();
    }

    private void printResult(ReservationDay reservationDay, Orders orders, EventManager eventManager) {
        printIntroMessage(reservationDay);
        printOrderedMenus(orders);
        printTotalAmountWithNoDiscount(orders);
        printGiftMenu(orders);
        printBenefitsDetails(eventManager);
        printTotalBenefitedAmount(eventManager);
        printEstimatedAmountWithDiscount(eventManager);
        printEventBadge(eventManager);
    }

    private void printIntroMessage(ReservationDay reservationDay) {
        outputView.printIntroMessage(reservationDayController.createEventBenefitsPreviousDto(reservationDay));
    }

    private void printOrderedMenus(Orders orders) {
        outputView.printOrderedMenus(ordersController.createOrderedMenusDto(orders));
    }

    private void printTotalAmountWithNoDiscount(Orders orders) {
        outputView.printTotalAmountWithNoDiscount(ordersController.createTotalAmountWithNoDiscountDto(orders));
    }

    private void printGiftMenu(Orders orders) {
        outputView.printGiftMenu(eventManagerService.createGiftDto(orders));
    }

    private void printBenefitsDetails(EventManager eventManager) {
        outputView.printBenefitsDetails(eventManagerService.createBenefitsDetailsDto(eventManager));
    }

    private void printTotalBenefitedAmount(EventManager eventManager) {
        outputView.printTotalBenefitedAmount(eventManagerService.createTotalBenefitedAmountDto(eventManager));
    }

    private void printEstimatedAmountWithDiscount(EventManager eventManager) {
        outputView.printEstimatedAmountWithDiscount(
                eventManagerService.createEstimatedAmountWithDiscountDto(eventManager));
    }

    private void printEventBadge(EventManager eventManager) {
        outputView.printEventBadge(eventManagerService.createEventBadgeDto(eventManager));
    }

    private void terminatePlanner() {
        Console.close();
    }
}