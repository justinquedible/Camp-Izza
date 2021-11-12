package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.data.RegisteredWeeksRepository;
import com.campizza.backend.campizzabackend.data.WeeksRepository;
import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.security.payloads.CamperAttendanceReport;
import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campizza.backend.campizzabackend.data.AttendanceRepository;

import java.math.BigInteger;
import java.util.*;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private WeeksRepository weeksRepository;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private RegisteredWeeksRepository registeredWeeksRepository;

    public List<Attendance> getAttendancesAll() {
        return attendanceRepository.findAll();
    }

    public void createAttendance(Attendance attendance) {
        attendanceRepository.saveAndFlush(attendance);
        // attendanceRepository.notifyAll();
    }


    public List<Camper> getActualAMAttendance(Date date, String group) {
        try {
            return camperRepository.findAllCampersAttendingByAMGroup(group, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Camper> getActualPMAttendance(Date date, String group) {
        try {
            return camperRepository.findAllCampersAttendingByPMGroup(group, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Camper> getExpectedAMAttendance(Date date, String group) {
        // System.out.println(date);
        try {
            return camperRepository.findAllAMCampersRegByGroup(group, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Camper> getExpectedPMAttendance(Date date, String group) {

        try {
            return camperRepository.findAllPMCampersRegByGroup(group, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<String> getBonusCamper(Date date, String group, String time) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Optional<Attendance> a = Optional.empty();
            switch (time) {
                case "am": {
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, false);
                    break;
                }
                case "pm": {
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, false, true);
                    break;
                }
                case "full": {
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, true);
                    break;
                }
            }
            if (a.isPresent()) {
                Attendance attendance = a.get();
                for (String bonusCamper : attendance.getBonusCampers()) {
                    String[] camperList = bonusCamper.split(",");
                    if(camperList[0].equals(group)) {

                        if(camperList.length == 1) { // Remove empty camper name
                            attendance.removeBonusCamper(bonusCamper);
                            attendanceRepository.saveAndFlush(attendance);
                        }
                        else {
                            result.add(bonusCamper);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addCamper(Camper camper, Date date) {
        Optional<Attendance> a = attendanceRepository.findByCurrDate(date);
        if (a.isPresent()) {
            // System.out.println("IS PRESENT");
            Attendance attendance = a.get();
            attendance.addCamper(camper);
            attendanceRepository.saveAndFlush(attendance);

        }
    }

    private Attendance initializeAttendanceRecords(Date date, String time, Optional<Attendance> a) {
        switch (time) {
            case "am":
                a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, false);
                if (!a.isPresent()) {
                    createAttendance(new Attendance(date, "am"));
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, false);
                }
                break;
            case "pm":
                a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, false, true);
                if (!a.isPresent()) {
                    createAttendance(new Attendance(date, "pm"));
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, false, true);
                }
                break;
            case "full":
                a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, true);
                if (!a.isPresent()) {
                    createAttendance(new Attendance(date, "full"));
                    a = attendanceRepository.findByCurrDateAndTimeAMAndTimePM(date, true, true);
                }
                break;
        }
        return a.get();
    }


    public void updateCamper(Long camperID, Date date, String time) throws InterruptedException {
        // Get current camper and old attendance (if present)
        Optional<Camper> c = camperRepository.findById(camperID);
        Optional<Attendance> a = attendanceRepository.findByCurrDateAndCampersIsContaining(date, c.get());

        // Delete old attendance
        if (a.isPresent()) {
            Attendance originalAttendance = a.get();
            // System.out.println("Attendance ID " + originalAttendance.getId());
            if (originalAttendance.containsCamper(c.get())) {
                originalAttendance.removeCamper(c.get());
                attendanceRepository.saveAndFlush(originalAttendance);
            }
        }
        // Add new attendance (do nothing if absent)
        if (!time.equals("absent")) {
            Attendance attendance = initializeAttendanceRecords(date, time, a);
            Camper camper = c.get();
            // System.out.println("New Attendance " + attendance.getId());
            // System.out.println("Camper " + camper.getFirstName());
            attendance.addCamper(camper);
            attendanceRepository.saveAndFlush(attendance);
        }
    }

    public void updateBonusCamper(String bonusCamperName, String bonusCamperGroup, Date date, String time) {
        // Get Current Attendance Record
        Optional<Attendance> a = attendanceRepository.findByCurrDateAndBonusCampers(date, bonusCamperGroup + "," + bonusCamperName);

        // Delete old attendance for bonus camper
        if (a.isPresent()) {
            Attendance originalAttendance = a.get();
            // System.out.println("Attendance ID " + originalAttendance.getId());
            if (originalAttendance.containsBonusCamper(bonusCamperGroup + "," + bonusCamperName)) {
                originalAttendance.removeBonusCamper(bonusCamperGroup + "," + bonusCamperName);
                attendanceRepository.saveAndFlush(originalAttendance);
            }
        }
        if(!time.equals("absent")) {
            Attendance attendance = initializeAttendanceRecords(date, time, a);
            attendance.addBonusCampers(bonusCamperGroup + "," + bonusCamperName);
            attendanceRepository.saveAndFlush(attendance);
        }
    }


    public Map<Long, Map<String, String>> getCamperAttendanceByProgramYear(Long camperID, Integer year) {
        CamperAttendanceReport response = new CamperAttendanceReport();


       List<Object[]> a = attendanceRepository.findAllAttendanceByCamperAndYear(camperID, year);

        Optional<RegisteredWeeks> p = registeredWeeksRepository.findByCurrentYear(year);

        RegisteredWeeks program = p.get();

        List<Weeks> weeks = program.getWeeks();
        for(Weeks week: weeks) {
            response.addWeek(week.getWeekId(), week.getStartDate().toString(), week.getEndDate().toString());
        }

        // System.out.println(a);
        assert a != null;
        for(Object[] object: a){
            // System.out.println(Arrays.toString(object));
            response.addAttribute(((BigInteger) object[3]).longValue(), (Boolean) object[0], (Boolean) object[1], ((Date) object[2]).toString());
        }
        // [am, pm, date, weekID]
        return response.getAttendanceReport();
    }
}
