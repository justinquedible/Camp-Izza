package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Counselor;

import java.util.List;

public interface CounselorService {
    List<Counselor> getCounselorsAll();

    void createCounselor (Counselor counselor);
}
