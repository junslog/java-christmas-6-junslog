package christmas.domain;

import static christmas.domain.constant.event.ChristmasPromotion.CHRISTMAS_D_DAY_PROMOTION;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasPromotionTest {
    @DisplayName("25일 이전의 날짜가 입력되면, 해당하는 크리스마스 디데이 프로모션 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1:1000", "2:1100", "3:1200", "25:3400", "24:3300", "23:3200",
            "15:2400", "16:2500", "19:2800", "7:1600", "11:2000", "13:2200"
    }, delimiter = ':')
    void 날짜가_입력되면_해당하는_크리스마스_디데이_프로모션_금액을_계산(int day, int expectedBenefitAmount) {
        assertThat(CHRISTMAS_D_DAY_PROMOTION.getBenefitAmount(day))
                .isEqualTo(expectedBenefitAmount);
    }
}