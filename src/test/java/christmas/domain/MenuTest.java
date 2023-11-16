package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.orders.Menu;
import christmas.domain.exception.InvalidOrdersException;
import christmas.domain.exception.message.InvalidOrdersExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {
    @DisplayName("메뉴 이름이 메뉴판에 없으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"시츄샐러드", "동양송이수프", "물파스", "백퍼콜라", "와이본스테이크", "바비큐치킨", "크림", "당근케이크"})
    void 메뉴_이름이_메뉴판에_없으면_예외_발생(String menuName) {
        assertThatThrownBy(() -> Menu.searchByName(menuName))
                .isInstanceOf(InvalidOrdersException.class)
                .hasMessageContaining(InvalidOrdersExceptionMessage.NOT_EXISTING_MENU.getMessage());
    }

    @DisplayName("메뉴 이름이 메뉴판에 있으면, 메뉴가 올바르게 반환된다.")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드", "타파스", "양송이수프", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라",
            "레드와인", "샴페인"})
    void 메뉴_이름이_메뉴판에_있으면_메뉴를_올바르게_반환(String menuName) {
        // given, when
        Menu menu = Menu.searchByName(menuName);
        // then
        assertThat(menu).isInstanceOf(Menu.class);
        assertThat(menu.getName()).isEqualTo(menuName);
    }
}