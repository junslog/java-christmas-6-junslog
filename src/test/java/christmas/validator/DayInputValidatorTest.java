package christmas.validator;

import static christmas.view.input.constant.InputSymbolConstant.DAY_SYMBOL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.DayInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.exception.message.DayInputExceptionMessage;
import christmas.view.input.validator.DayInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DayInputValidatorTest {
    private DayInputValidator dayInputValidator;

    @BeforeEach
    void setUp() {
        dayInputValidator = new DayInputValidator();
    }

    @DisplayName("예약 날짜가 비어있는 입력값일 때 예외가 발생한다.")
    @Test
    void 예약_날짜가_비어있는_값일때_예외_발생() {
        assertThatThrownBy(() -> dayInputValidator.validate(""))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(),
                        DAY_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 제거 후 날짜 길이가 3글자 이상이면, 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {" 0    3    0 ", "0 0   2", "0 0   0  0 4"})
    void 공백_제거_후_예약_날짜_입력값이_3글자_이상이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> dayInputValidator.validate(userInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(),
                                DAY_SYMBOL.getSymbol()));
    }

    @DisplayName("예약 날짜가 숫자형식이 아닌 입력값일 때 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"1a", "1s", "kw", "@!", "-", "a-", "dy", "!a"})
    void 예약_날짜가_숫자_형식이_아닌_입력값이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> dayInputValidator.validate(userInput))
                .isInstanceOf(DayInputException.class)
                .hasMessageContaining(DayInputExceptionMessage.NOT_NUMERIC_TYPE.getMessage());
    }

    @DisplayName("예약 날짜가 양수가 아닌 숫자일 때 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"-1", "-2", "-5", "-7", "-9", "-8", "-3", "-4"})
    void 예약_날짜가_양수가_아닌_숫자이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> dayInputValidator.validate(userInput))
                .isInstanceOf(DayInputException.class)
                .hasMessageContaining(DayInputExceptionMessage.NOT_POSITIVE.getMessage());
    }
}