package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.RegisteredWeeks;

public class SessionPriceResponse {
    int pricing_base_rate;
    int pricing_extra_rate;
    int extra_shirts;
    int extended_rate;

    public SessionPriceResponse() {
    }

    public SessionPriceResponse(RegisteredWeeks program) {
        this.extended_rate = program.getExtendedCareRate();
        this.pricing_base_rate = program.getPricingBaseRate();
        this.extra_shirts = program.getShirtPrice();
        this.pricing_extra_rate = program.getPricingExtraRate();
    }

    public int getPricing_base_rate() {
        return pricing_base_rate;
    }

    public void setPricing_base_rate(int pricing_base_rate) {
        this.pricing_base_rate = pricing_base_rate;
    }

    public int getPricing_extra_rate() {
        return pricing_extra_rate;
    }

    public void setPricing_extra_rate(int pricing_extra_rate) {
        this.pricing_extra_rate = pricing_extra_rate;
    }

    public int getExtra_shirts() {
        return extra_shirts;
    }

    public void setExtra_shirts(int extra_shirts) {
        this.extra_shirts = extra_shirts;
    }

    public int getExtended_rate() {
        return extended_rate;
    }

    public void setExtended_rate(int extended_rate) {
        this.extended_rate = extended_rate;
    }
}
