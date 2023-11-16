package christmas;

import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(new InputView(), new OutputView());
        eventPlanner.execute();
    }
}