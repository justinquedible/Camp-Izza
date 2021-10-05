package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.YearlySessionPricingRepository;
import com.campizza.backend.campizzabackend.model.YearlySessionPricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearlySessionPricingImpl implements YearlySessionPricingService{

    @Autowired
    private YearlySessionPricingRepository yearlySessionPricingRepository;

    public List<YearlySessionPricing> getYearlySessionPricingAll() {
        return (List<YearlySessionPricing>) yearlySessionPricingRepository.findAll();
    }

    public void createYearlySessionPricing(YearlySessionPricing yearlySessionPricing) {
        yearlySessionPricingRepository.saveAndFlush(yearlySessionPricing);
    }
}
