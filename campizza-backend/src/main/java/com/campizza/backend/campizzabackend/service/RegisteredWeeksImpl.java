package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.data.HolidayRepository;
import com.campizza.backend.campizzabackend.data.RegisteredWeeksRepository;
import com.campizza.backend.campizzabackend.data.WeeksRepository;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Holiday;
import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.security.payloads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RegisteredWeeksImpl implements RegisteredWeeksService {

    @Autowired
    private RegisteredWeeksRepository registeredWeeksRepository;

    @Autowired
    private WeeksRepository weeksRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private CamperRepository camperRepository;

    public List<RegisteredWeeks> getRegisteredWeeksAll() {
        return registeredWeeksRepository.findAll();
    }

    public void alterGroupLimit(SessionLimitRequest request){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(request.getYear());
        RegisteredWeeks reg = program.get();


        reg.setCocoAMLimit(request.getCocoAMLimit());
        reg.setCocoPMLimit(request.getCocoPMLimit());
        reg.setDateAMLimit(request.getDatesAMLimit());
        reg.setDatePMLimit(request.getDatesPMLimit());
        reg.setTreeAMLimit(request.getTreeAMLimit());
        reg.setTreePMLimit(request.getTreePMLimit());
        reg.setLeadAMLimit(request.getLeadAMLimit());
        reg.setLeadPMLimit(request.getLeadPMLimit());

        registeredWeeksRepository.saveAndFlush(reg);
    }

    public void setSessionPrice(SessionPriceRequest request){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(request.getYear());
        RegisteredWeeks reg = program.get();

        reg.setShirtPrice(request.getExtra_shirts());
        reg.setExtendedCareRate(request.getExtended_rate());
        reg.setPricingBaseRate(request.getPricing_base_rate());
        reg.setPricingExtraRate(request.getPricing_extra_rate());

        registeredWeeksRepository.saveAndFlush(reg);
    }

    public void editWeek(Long id, String start, String end) throws ParseException {
        Optional<Weeks> week = weeksRepository.findById(id);
        Weeks realWeek = week.get();
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        Date starts = formatter1.parse(start);
        Date ends = formatter1.parse(end);

        realWeek.setStartDate(starts);
        realWeek.setEndDate(ends);
        weeksRepository.saveAndFlush(realWeek);
    }

    public void setCutoff(SessionEditWeekRequest request, int year) throws ParseException {
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        Date realCutoff = formatter1.parse(request.getCutoff());

        reg.setEarly_cutoff(realCutoff);
        reg.updateSessionTimes(request);

        registeredWeeksRepository.saveAndFlush(reg);
    }

    public SessionLimitResponse getGroupLimits(int year){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();

        SessionLimitResponse groupLimits = new SessionLimitResponse(reg);
        return groupLimits;
    }

    public SessionPriceResponse getSessionPrice(int year){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();

        SessionPriceResponse sessionPrice = new SessionPriceResponse(reg);
        return sessionPrice;
    }

    public void createRegisteredWeeks(RegisteredWeeks registeredWeeks) {
        registeredWeeksRepository.saveAndFlush(registeredWeeks);
    }

    public void createWeeks(Long id, Weeks week) {
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findById(id);
        RegisteredWeeks reg = program.get();
        weeksRepository.saveAndFlush(week);

        reg.addWeek(week);
        registeredWeeksRepository.saveAndFlush(reg);
    }

    public void createWeeksYear(int year, Weeks week) {
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();
        weeksRepository.saveAndFlush(week);

        reg.addWeek(week);
        registeredWeeksRepository.saveAndFlush(reg);
    }

    public List<Weeks> getProgramWeeks(Long id){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findById(id);
        RegisteredWeeks reg = program.get();

        return  reg.getWeeks();

    }

    public SessionInfoResponse getProgramInfo (int year){
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();

        return  new SessionInfoResponse(reg);

    }

    public List<Weeks> getWeeksAll(int year) {
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        RegisteredWeeks reg = program.get();

        return  reg.getWeeks();
    }
    public Set<Camper> getCampersInProgramYear(int year) {
        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
        Set<Camper> camperSet = new HashSet<>();
        if (program.isPresent()) {
            RegisteredWeeks reg = program.get();


            List<Weeks> weeksList = reg.getWeeks();

            for (Weeks weeks : weeksList) {

                camperSet.addAll(weeks.getCampersAM());
                camperSet.addAll(weeks.getCampersPM());

            }
        }
        return camperSet;
    }

    public Set<Camper> getCampersInAllPrograms() {
        Set<Camper> camperSet = new HashSet<>();
        List<RegisteredWeeks> programs = registeredWeeksRepository.findAll();
        for (RegisteredWeeks p : programs) {
            for (Weeks weeks : p.getWeeks()) {

                camperSet.addAll(weeks.getCampersAM());
                camperSet.addAll(weeks.getCampersPM());
            }
        }
        return camperSet;

    }

    public List<Holiday> getHolidays(Long id){
        Optional<Weeks> week = weeksRepository.findById(id);
        Weeks realWeek = week.get();

        return realWeek.getHolidays();
    }

    public void setHolidays(Long id, String holiday) throws ParseException {
        Optional<Weeks> week = weeksRepository.findById(id);
        Weeks realWeek = week.get();
        // System.out.println("In set Holiday");
        // System.out.println(holiday);
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        Date holidays = formatter1.parse(holiday);
        // System.out.println(holidays);
        Holiday finalHoliday = new Holiday(holidays);

        realWeek.addHoliday(finalHoliday);
        holidayRepository.saveAndFlush(finalHoliday);

        weeksRepository.saveAndFlush(realWeek);

    }

    public void removeHolidays(Long id, Long holidayID){
        Optional<Holiday> holiday = holidayRepository.findById(holidayID);
        Holiday realHoliday = holiday.get();
        Optional<Weeks> week = weeksRepository.findById(id);
        Weeks realWeek = week.get();

        realWeek.removeHoliday(realHoliday);
        weeksRepository.saveAndFlush(realWeek);
    }

//    public List<Weeks> getCampersRegistration(int year, Long camperID) {
//        Optional<RegisteredWeeks> program = registeredWeeksRepository.findByCurrentYear(year);
//        Optional<Camper> c = camperRepository.findById(camperID);
//        Camper camper = c.get();
//        if (program.isPresent()) {
//            RegisteredWeeks reg = program.get();
//            List<Weeks> weeksList = reg.getWeeks();
//            for (Weeks weeks : weeksList) {
//                if(weeks.getCampersAM().contains(camper)) {
//
//                }
//            }
//    }
}
