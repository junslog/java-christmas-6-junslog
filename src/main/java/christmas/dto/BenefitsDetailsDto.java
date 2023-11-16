package christmas.dto;

import java.util.Collections;
import java.util.Map;

public class BenefitsDetailsDto {
    private final Map<String, Integer> benefitsDetails;

    private BenefitsDetailsDto(Map<String, Integer> benefitsDetails) {
        this.benefitsDetails = benefitsDetails;
    }

    public static BenefitsDetailsDto from(Map<String, Integer> benefitsDetails) {
        return new BenefitsDetailsDto(benefitsDetails);
    }

    public Map<String, Integer> getBenefitsDetails() {
        return Collections.unmodifiableMap(benefitsDetails);
    }

    public boolean isEmpty() {
        return benefitsDetails.isEmpty();
    }
}