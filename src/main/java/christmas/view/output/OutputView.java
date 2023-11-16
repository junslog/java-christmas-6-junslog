package christmas.view.output;

import static christmas.view.output.constant.OutputFormatConstant.BENEFITS_DETAILS_PRINT_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.ESTIMATED_AMOUNT_WITH_DISCOUNT_PRINT_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.GIFT_PRINT_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.ORDERED_MENUS_PRINT_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.PRICE_FORMAT_STYLE;
import static christmas.view.output.constant.OutputFormatConstant.SHOW_RESULT_INTRO_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.TOTAL_AMOUNT_WITH_NO_DISCOUNT_PRINT_FORMAT;
import static christmas.view.output.constant.OutputFormatConstant.TOTAL_BENEFITED_AMOUNT_PRINT_FORMAT;
import static christmas.view.output.constant.OutputMessageConstant.GREETING;
import static christmas.view.output.constant.OutputMessageConstant.INSERT_ORDERS;
import static christmas.view.output.constant.OutputMessageConstant.INSERT_RESERVATION_DAY;
import static christmas.view.output.constant.OutputSymbolConstant.BENEFITS_DETAILS;
import static christmas.view.output.constant.OutputSymbolConstant.ESTIMATED_AMOUNT_WITH_DISCOUNT;
import static christmas.view.output.constant.OutputSymbolConstant.EVENT_BADGE;
import static christmas.view.output.constant.OutputSymbolConstant.GIFT_MENU;
import static christmas.view.output.constant.OutputSymbolConstant.NEW_LINE;
import static christmas.view.output.constant.OutputSymbolConstant.NO_BENEFITS;
import static christmas.view.output.constant.OutputSymbolConstant.NO_EVENT_BADGE;
import static christmas.view.output.constant.OutputSymbolConstant.NO_GIFT;
import static christmas.view.output.constant.OutputSymbolConstant.ORDERED_MENUS;
import static christmas.view.output.constant.OutputSymbolConstant.TOTAL_AMOUNT_WITH_NO_DISCOUNT;
import static christmas.view.output.constant.OutputSymbolConstant.TOTAL_BENEFITED_AMOUNT;

import christmas.dto.BenefitsDetailsDto;
import christmas.dto.EstimatedAmountWithDiscountDto;
import christmas.dto.EventBadgeDto;
import christmas.dto.EventBenefitsPreviewDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderedMenusDto;
import christmas.dto.TotalAmountWithNoDiscountDto;
import christmas.dto.TotalBenefitedAmountDto;
import java.text.DecimalFormat;

public class OutputView {
    public void printGreetingMessage() {
        print(GREETING.getMessage());
        printLine();
    }

    public void askToInsertReservationDay() {
        print(INSERT_RESERVATION_DAY.getMessage());
        printLine();
    }

    public void askToInsertOrders() {
        print(INSERT_ORDERS.getMessage());
        printLine();
    }

    public void printIntroMessage(EventBenefitsPreviewDto eventBenefitsPreviewDto) {
        printFormatted(SHOW_RESULT_INTRO_FORMAT.getFormat(), eventBenefitsPreviewDto.getDay());
        printLine();
        printLine();
    }

    public void printOrderedMenus(OrderedMenusDto orderedMenusDto) {
        print(ORDERED_MENUS.getSymbol());
        printLine();
        orderedMenusDto.getOrders().forEach(this::printOrderedMenu);
    }

    private void printOrderedMenu(final String menuName, final int menuCount) {
        printFormatted(ORDERED_MENUS_PRINT_FORMAT.getFormat(), menuName, menuCount);
        printLine();
    }

    public void printTotalAmountWithNoDiscount(TotalAmountWithNoDiscountDto totalAmountWithNoDiscountDto) {
        printLine();
        print(TOTAL_AMOUNT_WITH_NO_DISCOUNT.getSymbol());
        printLine();
        printFormatted(TOTAL_AMOUNT_WITH_NO_DISCOUNT_PRINT_FORMAT.getFormat(),
                formatPrice(totalAmountWithNoDiscountDto.getAmount()));
        printLine();
    }

    public void printGiftMenu(GiftDto giftDto) {
        printLine();
        print(GIFT_MENU.getSymbol());
        printLine();
        printGift(giftDto);
    }

    private void printGift(GiftDto giftDto) {
        if (giftDto.isEmpty()) {
            print(NO_GIFT.getSymbol());
            printLine();
        }
        giftDto.getGift().forEach((giftName, giftCount) -> {
                    printFormatted(GIFT_PRINT_FORMAT.getFormat(), giftName, giftCount);
                    printLine();
                }
        );
    }

    public void printBenefitsDetails(BenefitsDetailsDto benefitsDetailsDto) {
        printLine();
        print(BENEFITS_DETAILS.getSymbol());
        printLine();
        printBenefitNamesAndAmount(benefitsDetailsDto);
    }

    private void printBenefitNamesAndAmount(BenefitsDetailsDto benefitsDetailsDto) {
        if (benefitsDetailsDto.isEmpty()) {
            print(NO_BENEFITS.getSymbol());
            printLine();
        }
        benefitsDetailsDto.getBenefitsDetails().forEach((benefitName, benefitAmount) -> {
                    printFormatted(BENEFITS_DETAILS_PRINT_FORMAT.getFormat(), benefitName, formatPrice(benefitAmount));
                    printLine();
                }
        );
    }

    public void printTotalBenefitedAmount(TotalBenefitedAmountDto totalBenefitedAmountDto) {
        printLine();
        print(TOTAL_BENEFITED_AMOUNT.getSymbol());
        printLine();
        printFormatted(TOTAL_BENEFITED_AMOUNT_PRINT_FORMAT.getFormat(),
                formatPrice(-totalBenefitedAmountDto.getAmount()));
        printLine();
    }

    public void printEstimatedAmountWithDiscount(EstimatedAmountWithDiscountDto estimatedAmountWithDiscountDto) {
        printLine();
        print(ESTIMATED_AMOUNT_WITH_DISCOUNT.getSymbol());
        printLine();
        printFormatted(ESTIMATED_AMOUNT_WITH_DISCOUNT_PRINT_FORMAT.getFormat(),
                formatPrice(estimatedAmountWithDiscountDto.getAmount()));
        printLine();
    }

    public void printEventBadge(EventBadgeDto eventBadgeDto) {
        printLine();
        print(EVENT_BADGE.getSymbol());
        printLine();
        printEventBadgeName(eventBadgeDto);
    }

    private void printEventBadgeName(EventBadgeDto eventBadgeDto) {
        if (eventBadgeDto.isEmpty()) {
            print(NO_EVENT_BADGE.getSymbol());
        }
        if (!eventBadgeDto.isEmpty()) {
            print(eventBadgeDto.getBadgeName());
        }
    }

    public void printErrorMessage(final String message) {
        print(message);
        printLine();
    }

    private void print(final String message) {
        System.out.print(message);
    }

    private void printLine() {
        System.out.print(NEW_LINE.getSymbol());
    }

    private void printFormatted(final String format, final Object... args) {
        print(String.format(format, args));
    }

    private String formatPrice(final int price) {
        return new DecimalFormat(PRICE_FORMAT_STYLE.getFormat())
                .format(price);
    }
}