package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.parser.InputParser;
import java.util.Map;

public class InputView {
    private final InputParser inputParser;

    public InputView() {
        inputParser = new InputParser();
    }

    public int getDay() {
        return 0;
    }

    public Map<String, Integer> getOrders() {
        return null;
    }

    private String readLine() {
        return Console.readLine();
    }
}