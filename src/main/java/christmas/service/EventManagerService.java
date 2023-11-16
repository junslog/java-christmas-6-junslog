package christmas.service;


import static christmas.domain.constant.event.EventNumberConstant.GIFT_CHAMPAGNE_COUNT;

import christmas.domain.EventManager;
import christmas.domain.Orders;
import christmas.domain.constant.orders.Menu;
import christmas.dto.BenefitsDetailsDto;
import christmas.dto.EstimatedAmountWithDiscountDto;
import christmas.dto.EventBadgeDto;
import christmas.dto.GiftDto;
import christmas.dto.TotalBenefitedAmountDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventManagerService {
    public GiftDto createGiftDto(Orders orders) {
        Map<String, Integer> gifts = new LinkedHashMap<>();
        if (orders.isGiftEventApplicable()) {
            makeGifts(gifts);
        }
        return GiftDto.from(gifts);
    }

    private void makeGifts(Map<String, Integer> gifts) {
        gifts.put(Menu.CHAMPAGNE.getName(), GIFT_CHAMPAGNE_COUNT.getValue());
    }

    public BenefitsDetailsDto createBenefitsDetailsDto(EventManager eventManager) {
        return BenefitsDetailsDto.from(eventManager.createBenefitsDetails());
    }

    public TotalBenefitedAmountDto createTotalBenefitedAmountDto(EventManager eventManager) {
        return TotalBenefitedAmountDto.from(eventManager.calculateTotalBenefitedAmount());
    }

    public EstimatedAmountWithDiscountDto createEstimatedAmountWithDiscountDto(EventManager eventManager) {
        return EstimatedAmountWithDiscountDto.from(eventManager.calculateEstimatedOrdersAmountWithDiscount());
    }

    public EventBadgeDto createEventBadgeDto(EventManager eventManager) {
        return EventBadgeDto.from(eventManager.issueEventBadge());
    }
}