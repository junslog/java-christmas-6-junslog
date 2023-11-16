package christmas.controller;

import christmas.domain.ReservationDay;
import christmas.domain.exception.InvalidReservationDayException;
import christmas.dto.EventBenefitsPreviewDto;
import christmas.service.ReservationDayService;
import christmas.view.input.InputView;
import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.DayInputException;
import christmas.view.output.OutputView;

public class ReservationDayController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ReservationDayService reservationDayService;

    public ReservationDayController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        reservationDayService = new ReservationDayService();
    }

    public ReservationDay insertReservationDay() {
        try {
            int reservationDay = askToInsertReservationDay();
            return reservationDayService.createReservationDay(reservationDay);
        } catch (BasicInputException | DayInputException | InvalidReservationDayException e) {
            outputView.printErrorMessage(e.getMessage());
            return insertReservationDay();
        }
    }

    private int askToInsertReservationDay() throws BasicInputException, DayInputException {
        outputView.askToInsertReservationDay();
        return inputView.getReservationDay();
    }

    public EventBenefitsPreviewDto createEventBenefitsPreviousDto(ReservationDay reservationDay) {
        return reservationDayService.createEventBenefitsPreviewDto(reservationDay);
    }
}