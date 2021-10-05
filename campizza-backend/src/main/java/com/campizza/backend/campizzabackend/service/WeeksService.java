package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Weeks;

import java.util.List;

public interface WeeksService {
    List<Weeks>  getWeeksAll();

    void createWeeks(Weeks weeks);

    void addCamperAM(Long camperID, Long weekID);

    void addCamperPM(Long camperID, Long weekID);

    void addAttendanceDate(Long attendanceID, Long weekID);
}
