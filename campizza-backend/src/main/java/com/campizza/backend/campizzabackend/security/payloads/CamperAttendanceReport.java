package com.campizza.backend.campizzabackend.security.payloads;

import java.util.HashMap;
import java.util.Map;

public class CamperAttendanceReport {



    Map<Long, Map<String, String>> attendanceReport = new HashMap<>();


    // [am, pm, date, weekID]
    public void addAttribute(Long weekID, Boolean am, Boolean pm, String date) {
        if(am && pm) {
            attendanceReport.get(weekID).put(date, "full");
        }
        else if (am) {
            attendanceReport.get(weekID).put(date, "am");
        }
        else if (pm) {
            attendanceReport.get(weekID).put(date, "pm");
        }

    }

    public void addWeek(Long weekID, String startDate, String endDate) {
        attendanceReport.put(weekID, new HashMap<String,String>());
        attendanceReport.get(weekID).put("startDate", startDate);
        attendanceReport.get(weekID).put("endDate", endDate);
    }

    public Map<Long, Map<String, String>> getAttendanceReport() {
        return attendanceReport;
    }
}
