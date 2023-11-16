package christmas.domain;

import static christmas.domain.constant.event.ChristmasPromotion.CHRISTMAS_D_DAY_PROMOTION;
import static christmas.domain.constant.event.EventNumberConstant.NONE_PROMOTION_APPLIED_AMOUNT;
import static christmas.domain.constant.event.Promotion.GIFT_PROMOTION;
import static christmas.domain.constant.event.Promotion.SPECIAL_PROMOTION;
import static christmas.domain.constant.event.Promotion.WEEKDAY_PROMOTION;
import static christmas.domain.constant.event.Promotion.WEEKEND_PROMOTION;

import christmas.domain.constant.event.EventBadge;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private final ReservationDay reservationDay;
    private final Orders orders;

    private EventManager(ReservationDay reservationDay, Orders orders) {
        this.reservationDay = reservationDay;
        this.orders = orders;
    }

    public static EventManager of(ReservationDay reservationDay, Orders orders) {
        return new EventManager(reservationDay, orders);
    }

    public Map<String, Integer> createBenefitsDetails() {
        Map<String, Integer> benefitsDetails = new LinkedHashMap<>();
        if (orders.isEventApplicable()) {
            return makeBenefitsDetails(benefitsDetails);
        }
        return Collections.unmodifiableMap(benefitsDetails);
    }

    private Map<String, Integer> makeBenefitsDetails(Map<String, Integer> benefitsDetails) {
        addChristmasPromotionDetails(benefitsDetails);
        addWeekdayPromotionDetails(benefitsDetails);
        addWeekendPromotionDetails(benefitsDetails);
        addSpecialPromotionDetails(benefitsDetails);
        addGiftPromotionDetails(benefitsDetails);
        return Collections.unmodifiableMap(benefitsDetails);
    }

    private void addChristmasPromotionDetails(Map<String, Integer> benefitsDetails) {
        if (reservationDay.isChristmasPromotionApplicable()) {
            benefitsDetails.put(CHRISTMAS_D_DAY_PROMOTION.getName(),
                    calculateChristmasPromotionBenefitAmount());
        }
    }

    private int calculateChristmasPromotionBenefitAmount() {
        return CHRISTMAS_D_DAY_PROMOTION.getBenefitAmount(reservationDay.getDay());
    }

    private void addWeekdayPromotionDetails(Map<String, Integer> benefitsDetails) {
        if (canAddWeekdayPromotion()) {
            benefitsDetails.put(WEEKDAY_PROMOTION.getName(),
                    calculateWeekdayPromotionBenefitAmount());
        }
    }

    private boolean canAddWeekdayPromotion() {
        return reservationDay.isWeekdayPromotionApplicable()
                && calculateWeekdayPromotionBenefitAmount()
                > NONE_PROMOTION_APPLIED_AMOUNT.getValue();
    }

    private int calculateWeekdayPromotionBenefitAmount() {
        int count = orders.getOrders().stream()
                .filter(Order::isWeekdayPromotionApplicable)
                .mapToInt(Order::getMenuCount)
                .sum();
        return WEEKDAY_PROMOTION.getBenefitAmount() * count;
    }

    private void addWeekendPromotionDetails(Map<String, Integer> benefitsDetails) {
        if (canAddWeekendPromotion()) {
            benefitsDetails.put(WEEKEND_PROMOTION.getName(),
                    calculateWeekendPromotionBenefitAmount());
        }
    }

    private boolean canAddWeekendPromotion() {
        return reservationDay.isWeekendPromotionApplicable()
                && calculateWeekendPromotionBenefitAmount()
                > NONE_PROMOTION_APPLIED_AMOUNT.getValue();
    }

    private int calculateWeekendPromotionBenefitAmount() {
        int count = orders.getOrders().stream()
                .filter(Order::isWeekendPromotionApplicable)
                .mapToInt(Order::getMenuCount)
                .sum();
        return WEEKEND_PROMOTION.getBenefitAmount() * count;
    }

    private void addSpecialPromotionDetails(Map<String, Integer> benefitsDetails) {
        if (reservationDay.isSpecialPromotionApplicable()) {
            benefitsDetails.put(SPECIAL_PROMOTION.getName(), calculateSpecialPromotionBenefitAmount());
        }
    }

    private int calculateSpecialPromotionBenefitAmount() {
        return SPECIAL_PROMOTION.getBenefitAmount();
    }

    private void addGiftPromotionDetails(Map<String, Integer> benefitsDetails) {
        if (orders.isGiftEventApplicable()) {
            benefitsDetails.put(GIFT_PROMOTION.getName(), calculateGiftPromotionBenefitAmount());
        }
    }

    private int calculateGiftPromotionBenefitAmount() {
        return GIFT_PROMOTION.getBenefitAmount();
    }

    public int calculateTotalBenefitedAmount() {
        return createBenefitsDetails().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateEstimatedOrdersAmountWithDiscount() {
        return orders.calculateTotalAmountWithNoDiscount() - calculateTotalDiscountedAmount();
    }

    private int calculateTotalDiscountedAmount() {
        if (orders.isGiftEventApplicable()) {
            return calculateTotalBenefitedAmount() - calculateGiftPromotionBenefitAmount();
        }
        return calculateTotalBenefitedAmount();
    }

    public String issueEventBadge() {
        return EventBadge.getBadgeNameByBenefitedPrice(calculateTotalBenefitedAmount());
    }
}