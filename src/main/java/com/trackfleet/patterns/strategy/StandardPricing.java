package com.trackfleet.patterns.strategy;
public class StandardPricing implements PricingStrategy {
    public double calculatePrice(double weight) { return weight * 5.0; }
}