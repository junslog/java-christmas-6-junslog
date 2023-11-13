package christmas.view.output;

import static christmas.view.output.constant.OutputConstant.NEW_LINE;
import static christmas.view.output.constant.OutputMessage.GREETING;
import static christmas.view.output.constant.OutputMessage.INSERT_ORDERS;
import static christmas.view.output.constant.OutputMessage.INSERT_RESERVATION_DAY;

public class OutputView {
    public void printGreetingMessage() {
        print(GREETING.getMessage());
        printLine();
    }

    public void askToInsertReservationDay() {
        print(INSERT_RESERVATION_DAY.getMessage());
        printLine();
    }

    public void askToInsertOrders() {
        print(INSERT_ORDERS.getMessage());
        printLine();
    }


    public void printLine() {
        System.out.print(NEW_LINE);
    }

    public void printErrorMessage(final String message) {
        print(message);
        printLine();
    }

    private <T> void print(final T message) {
        System.out.print(message);
    }
}