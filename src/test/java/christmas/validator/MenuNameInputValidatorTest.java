package christmas.validator;

import static christmas.view.input.constant.InputSymbolConstant.ORDER_SYMBOL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.input.exception.BasicInputException;
import christmas.view.input.exception.message.BasicInputExceptionMessageFormat;
import christmas.view.input.validator.MenuNameInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuNameInputValidatorTest {
    private MenuNameInputValidator menuNameInputValidator;

    @BeforeEach
    void setUp() {
        menuNameInputValidator = new MenuNameInputValidator();
    }

    @DisplayName("메뉴 이름이 비어있는 입력값일 때 예외가 발생한다.")
    @Test
    void 메뉴_이름이_비어있는_값일때_예외_발생() {
        assertThatThrownBy(() -> menuNameInputValidator.validate(""))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(String.format(BasicInputExceptionMessageFormat.EMPTY_FORMAT.getFormat(),
                        ORDER_SYMBOL.getSymbol()));
    }

    @DisplayName("공백 제거 후 메뉴 이름 길이가 31글자 이상이면, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스 타파스  ",
            "티본스테이크 티본스테이크 티본스테이크 티본스테이크 티본스테이크 티본스테이크 티본스테이크 "
            , "레드와인 레드와인 레드와인 레드와인 레드와인 레드와인 레드와인 레드와인 "})
    void 공백_제거_후_메뉴_이름_길이가_31글자_이상이면_예외_발생(String userInput) {
        assertThatThrownBy(() -> menuNameInputValidator.validate(userInput))
                .isInstanceOf(BasicInputException.class)
                .hasMessageContaining(
                        String.format(BasicInputExceptionMessageFormat.TOO_LONG_FORMAT.getFormat(),
                                ORDER_SYMBOL.getSymbol()));
    }
}