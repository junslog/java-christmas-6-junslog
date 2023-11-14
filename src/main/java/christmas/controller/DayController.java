package christmas.controller;

import christmas.domain.ReservationDay;
import christmas.domain.exception.InvalidDayException;
import christmas.dto.EventBenefitsPreviewDto;
import christmas.service.DayService;
import christmas.view.input.InputView;
import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.DayInputException;
import christmas.view.output.OutputView;

public class DayController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DayService dayService;

    public DayController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        dayService = new DayService();
    }

    public ReservationDay insertDay() {
        try {
            int reservationDay = askToInsertReservationDay();
            return dayService.createDay(reservationDay);
        } catch (BasicInputException | DayInputException | InvalidDayException e) {
            outputView.printErrorMessage(e.getMessage());
            return insertDay();
        }
    }

    private int askToInsertReservationDay() throws BasicInputException, DayInputException {
        outputView.askToInsertReservationDay();
        return inputView.getDay();
    }

    public EventBenefitsPreviewDto createEventBenefitsPreviousDto(ReservationDay day) {
        return dayService.createEvenetBenefitsPreviewDto(day);
    }
}