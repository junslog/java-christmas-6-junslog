package christmas.validator;

import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.exception.message.OrdersInputExceptionMessage;
import christmas.view.input.validator.MenuCountInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuCountInputValidatorTest {
    private MenuCountInputValidator menuCountInputValidator;

    @BeforeEach
    void setUp() {
        menuCountInputValidator = new MenuCountInputValidator();
    }

    @DisplayName("메뉴 개수가 비어있는 입력값일 때 예외가 발생한다.")
    @Test
    void 메뉴_개수가_비어있는_값일때_예외_발생() {
        assertThatThrownBy(() -> menuCountInputValidator.validate(""))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(),
                        ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 제거 후 메뉴 개수가 3글자 이상이면, 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {" 1    3    2 ", "4 3   2", "2 5   3  7 4"})
    void 공백_제거_후_메뉴_개수_입력값이_3글자_이상이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> menuCountInputValidator.validate(userInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(),
                                ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("메뉴 개수가 숫자형식이 아닌 입력값일 때 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1", "s1", "wk", "!@", "-/", "-a", "yd", "a!"})
    void 메뉴_개수가_숫자_형식이_아닌_입력값이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> menuCountInputValidator.validate(userInput))
                .isInstanceOf(OrdersInputException.class)
                .hasMessageContaining(OrdersInputExceptionMessage.NOT_NUMERIC_TYPE.getMessage());
    }

    @DisplayName("메뉴 개수가 양수가 아닌 숫자일 때 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"-1", "-2", "-5", "-7", "-9", "-8", "-3", "-4"})
    void 메뉴_개수가_양수가_아닌_숫자이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> menuCountInputValidator.validate(userInput))
                .isInstanceOf(OrdersInputException.class)
                .hasMessageContaining(OrdersInputExceptionMessage.NOT_POSITIVE.getMessage());
    }
}