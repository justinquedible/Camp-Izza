package com.campizza.backend.campizzabackend.security.payloads;

public class SessionPriceRequest {
    int year;
    int pricing_base_rate;
    int pricing_extra_rate;
    int extra_shirts;
    int extended_rate;

    public SessionPriceRequest() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
