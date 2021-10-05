package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.AttendanceRepository;
import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.data.WeeksRepository;
import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeeksImpl implements WeeksService{

    @Autowired
    private WeeksRepository weeksRepository;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    private Camper getCamper(Long camperID) {
        Optional<Camper> c = camperRepository.findById(camperID);
        return c.get();
    }

    public List<Weeks> getWeeksAll() {
        return (List<Weeks>) weeksRepository.findAll();
    }

    public void createWeeks(Weeks weeks) {
        weeksRepository.saveAndFlush(weeks);
    }

    public void addCamperAM(Long camperID, Long weekID) {
        Optional<Weeks> w = weeksRepository.findById(weekID);
        Weeks week = w.get();
        week.addCamperAM(getCamper(camperID));
        weeksRepository.saveAndFlush(week);
    }

    public void addCamperPM(Long camperID, Long weekID) {
        Optional<Weeks> w = weeksRepository.findById(weekID);
        Weeks week = w.get();
        week.addCamperPM(getCamper(camperID));
        weeksRepository.saveAndFlush(week);
    }

    public void addAttendanceDate(Long attendanceID, Long weekID) {
        Optional<Weeks> w = weeksRepository.findById(weekID);
        Optional<Attendance> a = attendanceRepository.findById(attendanceID);
        if(w.isPresent() && a.isPresent()) {
            Weeks week = w.get();
            Attendance attendance = a.get();
            week.addAttendance(attendance);
            weeksRepository.saveAndFlush(week);
        }
    }
}
