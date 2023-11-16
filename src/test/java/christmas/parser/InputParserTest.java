package christmas.parser;

import static christmas.view.input.constant.InputSymbolConstant.DAY_SYMBOL;
import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.parser.InputParser;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @DisplayName("예약 날짜에 공백이 포함된 경우, 공백을 제거한 값을 숫자 형식으로 바꾸어 돌려준다.")
    @ParameterizedTest
    @CsvSource(value = {"1             0:10", "2    3:23", "   1   7:17", "3     1:31", "     1    :1",
            " 3 0 :30"}, delimiter = ':')
    void 예약_날짜에_공백이_포함된_경우_공백을_제거한_숫자로_반환(String userInput, int expected) {
        assertThat(inputParser.parseReservationDay(userInput)).isEqualTo(expected);
    }

    @DisplayName("주문 내역에 공백이 포함된 경우, 공백을 제거한 값을 Map형식으로 바꾸어 돌려준다.")
    @ParameterizedTest
    @CsvSource(value = {"타   파 스 - 1,타파스,1", " 티  본  스테  이 크 - 2,티본스테이크,2", "제로 콜  라-10,제로콜라,10"}, delimiter = ',')
    void 주문_내역에_공백이_포함된_경우_공백을_제거한_값을_맵으로_반환(String userInput, String menuName, int menuCount) {
        assertThat(inputParser.parseOrders(userInput))
                .isEqualTo(Collections.singletonMap(menuName, menuCount));
    }

    @DisplayName("공백 포함 날짜 입력값의 길이가 2000글자 이상이면, 예외가 발생한다.")
    @Test
    void 공백_포함_날짜_입력값이_2000글자_이상이면_예외_발생() {
        String safeDayInput = "1" + " ".repeat(1000);
        String illegalDayInput = "1" + " ".repeat(2000);
        assertThat(inputParser.parseReservationDay(safeDayInput)).isEqualTo(1);
        assertThatThrownBy(() -> inputParser.parseReservationDay(illegalDayInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_WITH_BLANKS_FORMAT.getFormat(),
                                DAY_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 포함 주문내역 입력값의 길이가 2000글자 이상이면, 예외가 발생한다.")
    @Test
    void 공백_포함_주문내역_입력값이_2000글자_이상이면_예외_발생() {
        String userInput = "타파스-1" + " ".repeat(2000);
        assertThatThrownBy(() -> inputParser.parseOrders(userInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_WITH_BLANKS_FORMAT.getFormat(),
                                ORDER_SYMBOL.getSymbol()));
    }
}