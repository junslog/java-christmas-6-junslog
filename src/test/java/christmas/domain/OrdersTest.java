package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.exception.InvalidOrdersException;
import christmas.domain.exception.message.InvalidOrdersExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest {

    @DisplayName("주문 내역을 취합했을 때, 메뉴 개수가 20개가 넘어가면 예외가 발생한다.")
    @Test
    public void 주문_내역을_취합했을때_메뉴_개수가_20개가_넘어가면_예외_발생() {
        // given
        List<Order> orders = List.of(
                Order.of("시저샐러드", 3),
                Order.of("제로콜라", 10),
                Order.of("타파스", 2),
                Order.of("티본스테이크", 3),
                Order.of("초코케이크", 4)
        );
        // when, then
        assertThatThrownBy(() -> Orders.from(orders))
                .isInstanceOf(InvalidOrdersException.class)
                .hasMessageContaining(InvalidOrdersExceptionMessage.EXCEED_MENU_COUNTS_UPPER_LIMIT.getMessage());
    }

    @DisplayName("주문 내역을 취합했을 때, 주문 내역에 음료수만 있으면 예외가 발생한다.")
    @Test
    public void 주문_내역을_취합했을때_주문_내역에_음료수만_있으면_예외_발생() {
        // given
        List<Order> orders = List.of(
                Order.of("레드와인", 3),
                Order.of("제로콜라", 4),
                Order.of("샴페인", 5)
        );
        // when, then
        assertThatThrownBy(() -> Orders.from(orders))
                .isInstanceOf(InvalidOrdersException.class)
                .hasMessageContaining(InvalidOrdersExceptionMessage.MENUS_ONLY_CONTAIN_BEVERAGE.getMessage());
    }

    @DisplayName("주문 내역을 취합했을 때, 주문 금액이 10000원 이하면 모든 이벤트 적용 대상에서 제외된다.")
    @Test
    public void 주문_내역을_취합했을때_주문_금액이_10000원_이하면_모든_이벤트_적용_대상에서_제외() {
        // given
        List<Order> orders = List.of(
                Order.of("양송이수프", 1),
                Order.of("제로콜라", 1)
        );
        // when, then
        assertThat(Orders.from(orders).isEventApplicable()).isEqualTo(false);
    }

    @DisplayName("주문 내역을 취합했을 때, 주문 금액이 10000원 초과면 이벤트 적용 대상에 포함한다.")
    @Test
    public void 주문_내역을_취합했을때_주문_금액이_10000원_초과면_이벤트_적용_대상에_포함() {
        // given
        List<Order> orders = List.of(
                Order.of("시저샐러드", 2),
                Order.of("티본스테이크", 3)
        );
        // when, then
        assertThat(Orders.from(orders).isEventApplicable()).isEqualTo(true);
    }

    @DisplayName("주문 내역을 취합했을 때, 주문 금액이 12만원 미만이면 증정 이벤트 적용 대상에 제외된다.")
    @Test
    public void 주문_내역을_취합했을때_주문_금액이_12만원_미만이면_증정_이벤트_적용_대상에_포함() {
        // given
        List<Order> orders = List.of(
                Order.of("시저샐러드", 1),
                Order.of("크리스마스파스타", 1),
                Order.of("아이스크림", 1),
                Order.of("제로콜라", 1)
        );
        // when, then
        assertThat(Orders.from(orders).isGiftEventApplicable()).isEqualTo(false);
    }


    @DisplayName("주문 내역을 취합했을 때, 주문 금액이 12만원 이상이면 증정 이벤트 적용 대상에 포함한다.")
    @Test
    public void 주문_내역을_취합했을때_주문_금액이_12만원_이상이면_증정_이벤트_적용_대상에_포함() {
        // given
        List<Order> orders = List.of(
                Order.of("시저샐러드", 2),
                Order.of("바비큐립", 2),
                Order.of("티본스테이크", 1),
                Order.of("초코케이크", 1),
                Order.of("레드와인", 1)
        );
        // when, then
        assertThat(Orders.from(orders).isGiftEventApplicable()).isEqualTo(true);
    }

    @DisplayName("주문 내역을 이용해서 할인 전 총 주문 금액을 계산한다.")
    @Test
    public void 주문_내역을_이용해서_할인_전_총_주문_금액을_계산() {
        // given
        List<Order> orders = List.of(
                Order.of("시저샐러드", 1),
                Order.of("티본스테이크", 1),
                Order.of("아이스크림", 1),
                Order.of("초코케이크", 1),
                Order.of("레드와인", 1)
        );
        // when, then
        assertThat(Orders.from(orders).calculateTotalAmountWithNoDiscount()).isEqualTo(143000);
    }
}