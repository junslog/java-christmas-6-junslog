package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.event.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventBadgeTest {
    @DisplayName("혜택 금액에 따라 이벤트 배지를 발급한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1000:''", "4999:''", "5000:별", "8230:별", "9999:별", "10000:트리", "13200:트리",
            "14999:트리", "15000:트리", "19999:트리", "20000:산타", "20001:산타", "83000:산타"
    }, delimiter = ':')
    void 혜택_금액에_따라_이벤트_배지_발급(int totalBenefitedAmount, String eventBadgeName) {
        assertThat(EventBadge.getBadgeNameByBenefitedPrice(totalBenefitedAmount))
                .isEqualTo(eventBadgeName);
    }
}