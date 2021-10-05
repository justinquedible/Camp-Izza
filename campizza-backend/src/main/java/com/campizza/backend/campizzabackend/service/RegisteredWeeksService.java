package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Holiday;
import com.campizza.backend.campizzabackend.model. RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.security.payloads.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface RegisteredWeeksService {
    List< RegisteredWeeks>  getRegisteredWeeksAll();

    void createRegisteredWeeks( RegisteredWeeks  registeredWeeks);

    void createWeeks(Long program, Weeks weeks);

    void createWeeksYear(int year, Weeks weeks);

    List<Weeks> getProgramWeeks(Long program);

    List<Weeks> getWeeksAll(int year);

    SessionInfoResponse getProgramInfo (int year);

    Set<Camper> getCampersInProgramYear(int year);

    Set<Camper> getCampersInAllPrograms();

    SessionLimitResponse getGroupLimits(int year);

    SessionPriceResponse getSessionPrice(int year);

    void setSessionPrice(SessionPriceRequest request);

    void editWeek(Long id, String start, String end) throws ParseException;

    void alterGroupLimit(SessionLimitRequest request);
//    List<Weeks> getCampersRegistration(int year, Long camperID);

    List<Holiday> getHolidays(Long id);

     void setHolidays(Long id, String holiday) throws ParseException ;

    void removeHolidays(Long id, Long holidayID);

    void setCutoff(SessionEditWeekRequest request, int year) throws ParseException;
}
