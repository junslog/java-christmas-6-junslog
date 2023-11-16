package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.DayInputException;
import christmas.view.input.exception.OrdersInputException;
import christmas.view.input.parser.InputParser;
import java.util.Map;

public class InputView {
    private final InputParser inputParser;

    public InputView() {
        inputParser = new InputParser();
    }

    public int getReservationDay() throws BasicInputException, DayInputException {
        return inputParser.parseReservationDay(readLine());
    }

    public Map<String, Integer> getOrders() throws BasicInputException, OrdersInputException {
        return inputParser.parseOrders(readLine());
    }

    private String readLine() {
        return Console.readLine();
    }
}