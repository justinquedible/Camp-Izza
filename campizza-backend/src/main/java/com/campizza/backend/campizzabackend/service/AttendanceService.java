package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.security.payloads.CamperAttendanceReport;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AttendanceService {
    List<Attendance> getAttendancesAll();

    void createAttendance(Attendance attendance);

    void addCamper(Camper camper, Date date);

    List<Camper> getActualAMAttendance(Date date, String group);
    List<Camper> getActualPMAttendance(Date date, String group);

    List<Camper> getExpectedAMAttendance(Date date, String group);
    List<Camper> getExpectedPMAttendance(Date date, String group);

    List<String> getBonusCamper(Date date, String group, String time);

    void updateCamper(Long camperID, Date date, String time) throws InterruptedException;

    void updateBonusCamper(String bonusCamperName, String bonusCamperGroup, Date date, String time);

    Map<Long, Map<String, String>> getCamperAttendanceByProgramYear(Long camperID, Integer year);
}
