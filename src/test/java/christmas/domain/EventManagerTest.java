package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventManagerTest {
    @DisplayName("주문 내역과 날짜를 비교해서, 혜택 내역을 생산한다.")
    @ParameterizedTest
    @CsvSource({
            "4, '시저샐러드,3|제로콜라,2|레드와인,1|티본스테이크,3|초코케이크,4','평일 할인,8092|크리스마스 디데이 할인,1300|증정 이벤트,25000'",
            "3,'티본스테이크,1|바비큐립,1|초코케이크,2|제로콜라,1','크리스마스 디데이 할인,1200|평일 할인,4046|특별 할인,1000|증정 이벤트,25000'",
            "25,'타파스,1|티본스테이크,1|아이스크림,3|제로콜라,3','크리스마스 디데이 할인,3400|평일 할인,6069|특별 할인,1000'"
    })
    void 주문_내역과_날짜를_비교해서_혜택_내역을_생산(int day, String menuAndCounts, String expectedBenefitDetails) {
        // given
        EventManager eventManager = createEventManager(day, menuAndCounts);
        // when
        Map<String, Integer> benefitsDetails = eventManager.createBenefitsDetails();
        // then
        for (String benefit : expectedBenefitDetails.split("\\|")) {
            String[] parts = benefit.split(",");
            String benefitName = parts[0];
            int expectedAmount = Integer.parseInt(parts[1]);
            assertThat(benefitsDetails.get(benefitName)).isEqualTo(expectedAmount);
        }
    }

    @DisplayName("주문 내역에 날짜에 따른 할인 여부를 적용해서, 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "4, '시저샐러드,3|제로콜라,2|레드와인,1|티본스테이크,3|초코케이크,4','9392'",
            "3,'티본스테이크,1|바비큐립,1|초코케이크,2|제로콜라,1','6246'",
            "25,'타파스,1|티본스테이크,1|아이스크림,3|제로콜라,3','10469'"
    })
    void 주문_내역에_날짜에_따른_할인_여부를_적용해서_할인_금액_계산(int day, String menuAndCounts, int expectedDiscountAmount) {
        // given
        Orders orders = Orders.from(parseMenuAndCounts(menuAndCounts));
        EventManager eventManager = createEventManager(day, menuAndCounts);
        // when
        int discountAmount =
                orders.calculateTotalAmountWithNoDiscount() - eventManager.calculateEstimatedOrdersAmountWithDiscount();
        // then
        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    @DisplayName("혜택 내역의 금액을 합쳐서, 총 혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "4, '시저샐러드,3|제로콜라,2|레드와인,1|티본스테이크,3|초코케이크,4','34392'",
            "3,'티본스테이크,1|바비큐립,1|초코케이크,2|제로콜라,1','31246'",
            "25,'타파스,1|티본스테이크,1|아이스크림,3|제로콜라,3','10469'"
    })
    void 혜택_내역의_금액을_합쳐서_총_혜택_금액을_계산(int day, String menuAndCounts, int expectedDiscountAmount) {
        // given
        EventManager eventManager = createEventManager(day, menuAndCounts);
        // when
        int discountAmount = eventManager.calculateTotalBenefitedAmount();
        // then
        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    @DisplayName("총 혜택 금액을 기반으로 12월 이벤트 배지를 발급한다.")
    @ParameterizedTest
    @CsvSource({
            "4, '시저샐러드,3|제로콜라,2|레드와인,1|티본스테이크,3|초코케이크,4','산타'",
            "3,'티본스테이크,1|바비큐립,1|초코케이크,2|제로콜라,1','산타'",
            "25,'타파스,1|티본스테이크,1|아이스크림,3|제로콜라,3','트리'",
            "26,'타파스,1|크리스마스파스타,1|아이스크림,3|','별'",
            "26,'양송이수프,3|크리스마스파스타,1|아이스크림,1|',''"
    })
    void 총_혜택_금액을_기반으로_12월_이벤트_배지를_발급(int day, String menuAndCounts, String expectedEventBadge) {
        // given
        EventManager eventManager = createEventManager(day, menuAndCounts);
        // when
        String eventBadge = eventManager.issueEventBadge();
        // then
        assertThat(eventBadge).isEqualTo(expectedEventBadge);
    }

    private EventManager createEventManager(int day, String menuAndCounts) {
        ReservationDay reservationDay = ReservationDay.from(day);
        Orders orders = Orders.from(parseMenuAndCounts(menuAndCounts));
        return EventManager.of(reservationDay, orders);
    }

    private List<Order> parseMenuAndCounts(String menuAndCounts) {
        List<Order> orders = new ArrayList<>();
        String[] splitedMenusAndCounts = menuAndCounts.split("\\|");
        for (String splitedMenusAndCount : splitedMenusAndCounts) {
            String[] menuNameAndCount = splitedMenusAndCount.split(",");
            orders.add(Order.of(menuNameAndCount[0], Integer.parseInt(menuNameAndCount[1])));
        }
        return orders;
    }
}