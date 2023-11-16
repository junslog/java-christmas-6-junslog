package christmas.validator;

import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.exception.message.OrdersInputExceptionMessage;
import christmas.view.input.validator.OrderInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderInputValidatorTest {
    private OrderInputValidator orderInputValidator;

    @BeforeEach
    void setUp() {
        orderInputValidator = new OrderInputValidator();
    }

    @DisplayName("주문이 비어있는 입력값일 때 예외가 발생한다.")
    @Test
    void 주문이_비어있는_값일때_예외_발생() {
        assertThatThrownBy(() -> orderInputValidator.validate(""))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(),
                        ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 제거 후 주문 길이가 34글자 이상이면, 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스타파스-13",
            "티본스테이크티본스테이크티본스테이크티본스테이크티본스테이크티본스테이크-14",
            "아이스크림아이스크림아이스크림아이스크림아이스크림아이스크림아이스크림아이스크림-11"})
    void 공백_제거_후_주문_입력값이_34글자_이상이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> orderInputValidator.validate(userInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(),
                                ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("주문에 구분자(-)가 포함되어 있지 않으면 에외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스1", "티본스테이크~2", "바비큐립@12", "아이스크림", "시저샐러드#3", "초코케이크*4", "제로콜라!!", "레드와인$3"})
    void 주문_입력값에_구분자가_포함되어_있지_않으면_예외_발생(String userInput) {
        assertThatThrownBy(() -> orderInputValidator.validate(userInput))
                .isInstanceOf(OrdersInputException.class)
                .hasMessageContaining(OrdersInputExceptionMessage.INVALID_ORDER_FORMAT.getMessage());
    }

    @DisplayName("주문의 마지막 단어가 구분자(-)인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스1-", "티본스테이크-", "티본스테이크-1-", "시저샐러드-", "초코-케이크-"})
    void 주문_입력값의_마지막_단어가_구분자_이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> orderInputValidator.validate(userInput))
                .isInstanceOf(OrdersInputException.class)
                .hasMessageContaining(OrdersInputExceptionMessage.EMPTY_MENU_COUNT.getMessage());
    }
}