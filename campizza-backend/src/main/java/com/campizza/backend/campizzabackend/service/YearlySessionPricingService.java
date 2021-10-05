package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.YearlySessionPricing;

import java.util.List;

public interface YearlySessionPricingService {
    List<YearlySessionPricing>  getYearlySessionPricingAll();

    void createYearlySessionPricing(YearlySessionPricing yearlySessionPricing);
}
