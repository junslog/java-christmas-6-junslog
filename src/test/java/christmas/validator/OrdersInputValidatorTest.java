package christmas.validator;

import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.OrdersInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.exception.message.OrdersInputExceptionMessage;
import christmas.view.input.validator.OrdersInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrdersInputValidatorTest {
    private OrdersInputValidator ordersInputValidator;

    @BeforeEach
    void setUp() {
        ordersInputValidator = new OrdersInputValidator();
    }

    @DisplayName("주문 내역이 비어있는 입력값일 때 예외가 발생한다.")
    @Test
    void 주문_내역이_비어있는_값일때_예외_발생() {
        assertThatThrownBy(() -> ordersInputValidator.preValidate(""))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(),
                        ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 제거 후 주문 내역 길이가 1000글자 이상이면, 예외가 발생한다.")
    @Test
    void 공백_제거_후_주문내역_입력값이_1000글자_이상이면_예외_발생() {
        String illegalOrdersInput = "시저샐러드-1,".repeat(200);
        assertThatThrownBy(() -> ordersInputValidator.preValidate(illegalOrdersInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(), ORDER_SYMBOL.getSymbol());
    }

    @DisplayName("주문 내역을 취합했을 때, 중복된 주문 메뉴가 존재하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-1,타파스-4",
            "타파스-1,초코케이크-2,레드와인-1,샴페인-1,아이스크림-2,티본스테이크-1,초코케이크-1",
            "초코케이크-1,초코케이크-1,초코케이크-1,초코케이크-1,초코케이크-1,초코케이크-1",
            "시저샐러드-1,양송이수프-1,제로콜라-1,샴페인-1,크리스마스파스타-2,해산물파스타-1,시저샐러드-4"
    })
    void 중복_주문_메뉴_존재_시_예외_발생(String userInput) {
        assertThatThrownBy(() -> ordersInputValidator.postValidate(userInput))
                .isInstanceOf(OrdersInputException.class)
                .hasMessageContaining(OrdersInputExceptionMessage.DUPLICATED_MENUS.getMessage());
    }
}