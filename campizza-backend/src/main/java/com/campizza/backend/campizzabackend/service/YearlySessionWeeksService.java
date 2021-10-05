package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.YearlySessionWeeks;

import java.util.List;

public interface YearlySessionWeeksService {
    List<YearlySessionWeeks>  getYearlySessionWeeksAll();

    void createYearlySessionWeeks(YearlySessionWeeks yearlySessionWeeks);
}
