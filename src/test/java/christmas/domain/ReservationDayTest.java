package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.exception.InvalidReservationDayException;
import christmas.domain.exception.message.InvalidReservationDayExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReservationDayTest {
    @DisplayName("유효하지 않은 날짜 ( 1 ~ 31 사이의 숫자가 아닌 숫자 )를 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10, 300, 350, 500, 32})
    void 유효하지_않은_날짜_입력시_예외_발생(int day) {
        assertThatThrownBy(() -> ReservationDay.from(day))
                .isInstanceOf(InvalidReservationDayException.class)
                .hasMessageContaining(InvalidReservationDayExceptionMessage.NOT_IN_APPROPRIATE_RANGE.getMessage());
    }

    @DisplayName("날짜를 입력하면 크리스마스 D-Day 프로모션이 적용 가능한지 여부를 판단 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "10:true", "24:true", "31:false", "25:true", "26:false"}, delimiter = ':')
    void 날짜_입력시_크리스마스_디데이_프로모션_적용_가능_여부_판단_가능(int day, boolean expected) {
        // given, when
        ReservationDay reservationDay = ReservationDay.from(day);
        // then
        assertThat(reservationDay.isChristmasPromotionApplicable()).isEqualTo(expected);
    }

    @DisplayName("날짜를 입력하면 평일 할인이 적용 가능한지 여부를 판단 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"5:true", "6:true", "7:true", "8:false", "25:true", "29:false", "30:false",
            "31:true"}, delimiter = ':')
    void 날짜_입력시_평일_할인_적용_가능_여부_판단_가능(int day, boolean expected) {
        // given, when
        ReservationDay reservationDay = ReservationDay.from(day);
        // then
        assertThat(reservationDay.isWeekdayPromotionApplicable()).isEqualTo(expected);
    }

    @DisplayName("날짜를 입력하면 주말 할인이 적용 가능한지 여부를 판단 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "9:true", "15:true", "24:false", "30:true", "28:false", "31:false",
            "23:true"}, delimiter = ':')
    void 날짜_입력시_주말_할인_적용_가능_여부_판단_가능(int day, boolean expected) {
        // given, when
        ReservationDay reservationDay = ReservationDay.from(day);
        // then
        assertThat(reservationDay.isWeekendPromotionApplicable()).isEqualTo(expected);
    }

    @DisplayName("날짜를 입력하면 특별 할인이 적용 가능한지 여부를 판단 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"3:true", "10:true", "24:true", "26:false", "25:true", "27:false", "14:false",
            "17:true", "31:true"}, delimiter = ':')
    void 날짜_입력시_특별_할인_적용_가능_여부_판단_가능(int day, boolean expected) {
        // given, when
        ReservationDay reservationDay = ReservationDay.from(day);
        // then
        assertThat(reservationDay.isSpecialPromotionApplicable()).isEqualTo(expected);
    }
}