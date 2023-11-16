package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.exception.InvalidOrdersException;
import christmas.domain.exception.message.InvalidOrdersExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTest {
    @DisplayName("메뉴판에 있는 메뉴 이름과 수량을 주문하면 주문이 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"양송이수프-10", "타파스-3", "레드와인-5", "제로콜라-3", "샴페인-7", "바비큐립-10"}, delimiter = '-')
    void 메뉴판에_있는_메뉴_이름과_수량을_주문시_주문_생성(String menuName, int count) {
        // given, when
        Order order = Order.of(menuName, count);
        // then
        assertThat(order).isInstanceOf(Order.class);
    }

    @DisplayName("메뉴판에 없는 메뉴를 주문하면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"시져샐러드-10", "티스테이크-3", "산나물파스타-2", "논알콜콜라-3", "단오파스타-7", "연근케이크-10"}, delimiter = '-')
    void 메뉴판에_없는_메뉴이름으로_주문시_예외_발생(String menuName, int count) {
        assertThatThrownBy(() -> Order.of(menuName, count))
                .isInstanceOf(InvalidOrdersException.class)
                .hasMessageContaining(InvalidOrdersExceptionMessage.NOT_EXISTING_MENU.getMessage());
    }
}