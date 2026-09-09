package com.trackfleet.patterns.strategy;
public class ExpressPricing implements PricingStrategy {
    public double calculatePrice(double weight) { return weight * 10.0 + 15.0; } // Express premium
}