package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.YearlySessionWeeksRepository;
import com.campizza.backend.campizzabackend.model.YearlySessionWeeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearlySessionWeeksImpl implements YearlySessionWeeksService{

    @Autowired
    private YearlySessionWeeksRepository yearlySessionWeeksRepository;

    public List<YearlySessionWeeks> getYearlySessionWeeksAll() {
        return (List<YearlySessionWeeks>) yearlySessionWeeksRepository.findAll();
    }

    public void createYearlySessionWeeks(YearlySessionWeeks yearlySessionWeeks) {
        yearlySessionWeeksRepository.saveAndFlush(yearlySessionWeeks);
    }
}
