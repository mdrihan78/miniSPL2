package com.trackfleet.patterns.factory;
import com.trackfleet.patterns.strategy.*;

public class PricingStrategyFactory {
    public static PricingStrategy getStrategy(String type) {
        if ("Express".equalsIgnoreCase(type)) {
            return new ExpressPricing();
        }
        return new StandardPricing(); // Default
    }
}
