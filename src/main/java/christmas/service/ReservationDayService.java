package christmas.service;

import christmas.domain.ReservationDay;
import christmas.domain.exception.InvalidReservationDayException;
import christmas.dto.EventBenefitsPreviewDto;

public class ReservationDayService {
    public ReservationDay createReservationDay(final int day) throws InvalidReservationDayException {
        return ReservationDay.from(day);
    }

    public EventBenefitsPreviewDto createEventBenefitsPreviewDto(ReservationDay day) {
        return EventBenefitsPreviewDto.from(day.getDay());
    }
}