package com.campizza.backend.campizzabackend.controller;

import com.campizza.backend.campizzabackend.data.*;
import com.campizza.backend.campizzabackend.model.*;
import com.campizza.backend.campizzabackend.security.payloads.*;
import com.campizza.backend.campizzabackend.service.CamperService;
import com.campizza.backend.campizzabackend.service.RegisteredWeeksService;
import com.campizza.backend.campizzabackend.service.MedicalRecordService;
import com.campizza.backend.campizzabackend.service.WeeksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campers")
public class CamperController {
    @Autowired
    private CamperService camperService;

    @Autowired
    RegisteredWeeksService registeredWeeksService;

    @Autowired
    RegisteredWeeksRepository registeredWeeksRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CamperRepository camperRepository;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    WeeksRepository weeksRepository;

    @Autowired
    WeeksService weeksService;

    @Autowired
    ParentRepository parentRepository;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCampers() {
        return new ResponseEntity<>(camperService.getCampersAll(), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Object> addCamper(@Valid @RequestBody CamperRequest camperRequest) {
        //System.out.println(camperRequest.getFirstName());
        //System.out.println(camperRequest.getUserID());


        Optional<User> user = userRepository.findById(camperRequest.getUserID());
        User realUser = user.get();

        if (camperRepository.existsByFirstNameAndUser(camperRequest.getFirstName(), realUser)){
            Optional<Camper> camper = camperRepository.findByFirstNameAndUser(camperRequest.getFirstName(), realUser);
            Camper realCamper = camper.get();




            Optional<MedicalRecord> mR = medicalRecordRepository.findByFirstNameAndLastNameAndUser_Id(camperRequest.getFirstName(), camperRequest.getLastName(), camperRequest.getUserID());
            MedicalRecord realMedicalRecord = mR.get();
            realMedicalRecord.updateInfo(camperRequest, realUser, realCamper);
            medicalRecordService.createMedicalRecord(realMedicalRecord);

            Optional<HouseHold> parent = parentRepository.findByUser(realUser);

            if (parent.isPresent() == true){
                realCamper.updateInfo(camperRequest, realUser, realMedicalRecord, parent.get());
            }
            else{
                realCamper.updateInfo(camperRequest, realUser, realMedicalRecord);
            }

            camperService.createCamper(realCamper);

            return new ResponseEntity<>("Camper has been updated successfully!", HttpStatus.CREATED);
        }

        MedicalRecord medicalRecord = new MedicalRecord(camperRequest.getFirstName(), camperRequest.getLastName(),camperRequest.getDobDate(), camperRequest.getDoctorName(), camperRequest.getDoctorPhone(),
                camperRequest.getInsurance(), camperRequest.getPolicy_holder(), camperRequest.getIllnesses(), camperRequest.getIllnesses_names(), camperRequest.getAllergies(),
                camperRequest.getAllergy_names(), camperRequest.getMedication(),
                camperRequest.getMedication_names(), camperRequest.getActivities(), camperRequest.getActivity_names(), camperRequest.getMedical_treatments(),
                camperRequest.getMedical_treatment_names(), camperRequest.getImmunizations(), camperRequest.getTetanus_date(),camperRequest.getComment(), realUser);
        medicalRecordService.createMedicalRecord(medicalRecord);

        Optional<MedicalRecord> mR = medicalRecordRepository.findByFirstNameAndLastNameAndUser_Id(medicalRecord.getFirstName(), medicalRecord.getLastName(), camperRequest.getUserID());
        MedicalRecord realMedicalRecord = mR.get();

        Optional<HouseHold> parent = parentRepository.findByUser(realUser);
        Camper camper;
        if (parent.isPresent() == true){
            camper = new Camper(camperRequest, realUser, realMedicalRecord, parent.get());
        }
        else{
            camper = new Camper(camperRequest, realUser, realMedicalRecord);
        }

        assignGroup(camper, camper.getGradeNum());
        camperService.createCamper(camper);
        Optional<Camper> reacamper = camperRepository.findByFirstNameAndUser(camperRequest.getFirstName(), realUser);
        Camper realCamper = reacamper.get();
        realMedicalRecord.updateCamper(realCamper);

        return new ResponseEntity<>("Camper has been added successfully!", HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/addSchedule")
    public ResponseEntity<Object> addCamperSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {

        Optional<User> user = userRepository.findById(scheduleRequest.getUserId());
        User realUser = user.get();

        if (camperRepository.existsByFirstNameAndUser(scheduleRequest.getName(), realUser)){

            Optional<Camper> camper = camperRepository.findByFirstNameAndUser(scheduleRequest.getName(), realUser);
            Camper realCamper = camper.get();
            realCamper.setNumShirts(scheduleRequest.getNumShirts());
            camperService.createCamper(realCamper);

            List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(scheduleRequest.getCurrentYear());
            List<camperWeeks> weekStatuses = scheduleRequest.getCurrentWeeksRegistered();

            for (int i = 0; i < programWeeks.size(); i++){
                Weeks week = programWeeks.get(i);

                switch (weekStatuses.get(i).getStatus().toLowerCase()){
                    case "full":
                        if (week.getCampersAM().contains(realCamper) == false && week.getCampersPM().contains(realCamper) == false){

                            week.addAMCamper(realCamper);
                            week.addPMCamper(realCamper);
                            weeksService.createWeeks(week);
                        }
                        break;


                    case "am":

                        week.addAMCamper(realCamper);
                        weeksService.createWeeks(week);
                        break;

                    case "pm":

                        week.addPMCamper(realCamper);
                        weeksService.createWeeks(week);
                        break;

                    case "earlypm":
                        week.addEarlyCamperPM(realCamper);
                        weeksService.createWeeks(week);
                        break;

                    case "earlyam":
                        week.addEarlyCamperAM(realCamper);
                        weeksService.createWeeks(week);
                        break;

                    case "not-reg":
                        if (week.getCampersAM().contains(realCamper) == true ){

                            week.removeAMCamper(realCamper);
                            weeksService.createWeeks(week);
                        }
                        if (week.getCampersPM().contains(realCamper) == true ){

                            week.removePMCamper(realCamper);
                            weeksService.createWeeks(week);
                        }
                        break;

                }

            }

            return new ResponseEntity<>("Camper Schedule has been added successfully!", HttpStatus.CREATED);

        }


        return ResponseEntity.badRequest().body(new MessageResponse("Error: Camper Info was not found"));
    }

    @CrossOrigin
    @PostMapping("/getScheduleInfo")
    public ResponseEntity<Object> getScheduleInfo(@Valid @RequestBody ScheduleInfoRequest scheduleInfoRequest) {


        Optional<User> user = userRepository.findById(scheduleInfoRequest.getUserID());
        User realUser = user.get();

        Optional<RegisteredWeeks> registeredWeeks = registeredWeeksRepository.findByCurrentYear(scheduleInfoRequest.getCurrentYear());
        RegisteredWeeks realWeeks = registeredWeeks.get();

        if (camperRepository.existsByUser(realUser)){

            Optional<Camper> camper = camperRepository.findCamperByUser_Id_AndFirstName(scheduleInfoRequest.getUserID(), scheduleInfoRequest.getCamperName());
            Camper realCamper = camper.get();
            int numShirts = realCamper.getNumShirts();

            List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(scheduleInfoRequest.getCurrentYear());
            List<camperWeeks> weeksRegistered = new ArrayList<camperWeeks>();

            for (int i = 0; i < programWeeks.size(); i++){
                Weeks week = programWeeks.get(i);
                if (week.getCampersAM().contains(realCamper) &&  week.getCampersPM().contains(realCamper) ){
                    camperWeeks campWeek = new camperWeeks(week,"full",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersPM().contains(realCamper)){
                    camperWeeks campWeek = new camperWeeks(week,"pm",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersAM().contains(realCamper)){
                    camperWeeks campWeek = new camperWeeks(week,"am",i+1);
                    weeksRegistered.add(campWeek);
                }
                else{
                    camperWeeks campWeek = new camperWeeks(week,"not-reg",i+1);
                    weeksRegistered.add(campWeek);
                }
            }

            return ResponseEntity.ok(new ScheduleInfoResponse( realWeeks, weeksRegistered, numShirts));

        }


        return ResponseEntity.badRequest().body(new MessageResponse("Error: Camper Info was not found"));
    }

    @CrossOrigin
    @PostMapping("/getCamperScheduleInfo")
    public ResponseEntity<Object> getScheduleInfo(@Valid @RequestBody CamperScheduleInfoRequest camperScheduleInfoRequest) {

        // System.out.println(camperScheduleInfoRequest.getCamperID());
        // System.out.println(camperScheduleInfoRequest.getYear());

        Optional<RegisteredWeeks> registeredWeeks = registeredWeeksRepository.findByCurrentYear(camperScheduleInfoRequest.getYear());
        RegisteredWeeks realWeeks = registeredWeeks.get();

        if (camperRepository.existsById(camperScheduleInfoRequest.getCamperID())){

            Optional<Camper> camper = camperRepository.findCamperById(camperScheduleInfoRequest.getCamperID());
            Camper realCamper = camper.get();
            int numShirts = realCamper.getNumShirts();

            List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(camperScheduleInfoRequest.getYear());
            List<camperWeeks> weeksRegistered = new ArrayList<camperWeeks>();

            for (int i = 0; i < programWeeks.size(); i++){
                Weeks week = programWeeks.get(i);
                if (week.getCampersAM().contains(realCamper) &&  week.getCampersPM().contains(realCamper) ){
                    camperWeeks campWeek = new camperWeeks(week,"full",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersPM().contains(realCamper)){
                    camperWeeks campWeek = new camperWeeks(week,"pm",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersAM().contains(realCamper)){
                    camperWeeks campWeek = new camperWeeks(week,"am",i+1);
                    weeksRegistered.add(campWeek);
                }
                else{
                    camperWeeks campWeek = new camperWeeks(week,"not-reg",i+1);
                    weeksRegistered.add(campWeek);
                }
            }

            return ResponseEntity.ok(new ScheduleInfoResponse( realWeeks, weeksRegistered, numShirts));

        }


        return ResponseEntity.badRequest().body(new MessageResponse("Error: Camper Info was not found"));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Object> getParentCampers(@PathVariable("id") Long id) {
        // System.out.println(id);
        // System.out.println(camperService.getParentCampers(id));
        // System.out.println("accessed id");

        return new ResponseEntity<>(camperService.getParentCampers(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getCredit/{id}")
    public ResponseEntity<Object> getCamperCredit(@PathVariable("id") Long id) {
        Optional<Camper> camper = camperRepository.findCamperById(id);
        Camper realCamper = camper.get();


        return new ResponseEntity<>(realCamper.getCredit(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getPaid/{id}")
    public ResponseEntity<Object> getCamperPaid(@PathVariable("id") Long id) {
        Optional<Camper> camper = camperRepository.findCamperById(id);
        Camper realCamper = camper.get();


        return new ResponseEntity<>(realCamper.getPaid(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/setPaid/{id}")
    public ResponseEntity<Object> setCamperPaid(@PathVariable("id") Long id, @Valid @RequestBody CamperInfoRequest camperInfoRequest) {
        // System.out.println(camperInfoRequest.getAmount());
        Optional<Camper> camper = camperRepository.findCamperById(id);
        Camper realCamper = camper.get();
        realCamper.setPaid(camperInfoRequest.getAmount());
        camperRepository.saveAndFlush(realCamper);
        return ResponseEntity.ok().body(new MessageResponse("Camper Paid updated"));
    }

    @CrossOrigin
    @PostMapping("/setCredit/{id}")
    public ResponseEntity<Object> setCamperCredit(@PathVariable("id") Long id,@Valid @RequestBody   CamperInfoRequest camperInfoRequest) {
        Optional<Camper> camper = camperRepository.findCamperById(id);
        Camper realCamper = camper.get();
        realCamper.setCredit(camperInfoRequest.getAmount());
        camperRepository.saveAndFlush(realCamper);
        return ResponseEntity.ok().body(new MessageResponse("Camper Credit updated"));
    }

    @CrossOrigin
    @PostMapping("/getInfo")
    public ResponseEntity<Object> getCamperInfo(@Valid @RequestBody CamperInfoRequest camperInfoRequest) {


        Optional<User> user = userRepository.findById(camperInfoRequest.getUserID());
        User realUser = user.get();

        if (camperRepository.existsByUser(realUser)){

           Optional<Camper> camper = camperRepository.findCamperByUser_Id_AndFirstName(camperInfoRequest.getUserID(), camperInfoRequest.getFirstName());
           //Optional<Camper> camper = camperRepository.findCamperByUser_Id_AndId(camperInfoRequest.getUserID(), camperInfoRequest.getCamperID());
            Camper realCamper = camper.get();

            Optional<MedicalRecord> mR = medicalRecordRepository.findByFirstNameAndUser_Id(camperInfoRequest.getFirstName(), camperInfoRequest.getUserID());
            MedicalRecord realMedicalRecord = mR.get();

            return ResponseEntity.ok(new CamperInfoResponse(realCamper, realMedicalRecord));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: HouseHold Info was not found"));
    }

    @CrossOrigin
    @PostMapping("/getCamperInfo")
    public ResponseEntity<Object> getAdminCamperInfo(@Valid @RequestBody CamperInfoRequest camperInfoRequest) {



        if (camperRepository.existsById(camperInfoRequest.getCamperID())){

            Optional<Camper> camper = camperRepository.findCamperById(camperInfoRequest.getCamperID());
            //Optional<Camper> camper = camperRepository.findCamperByUser_Id_AndId(camperInfoRequest.getUserID(), camperInfoRequest.getCamperID());
            Camper realCamper = camper.get();

            Optional<MedicalRecord> mR = medicalRecordRepository.findByCamper(realCamper);
            MedicalRecord realMedicalRecord = mR.get();

            return ResponseEntity.ok(new CamperInfoResponse(realCamper, realMedicalRecord));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: HouseHold Info was not found"));
    }

    @CrossOrigin
    @Transactional
    @PostMapping("/del/{id}")
    public ResponseEntity<Object> deleteCamper(@PathVariable("id") Long id) {
        //Long i = new Long(id);
        System.out.println("deleting camper");
        System.out.println(id);
        camperService.removeCamper(id);

        return new ResponseEntity<>("Camper has been deleted successfully", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/getGroupInfo")
    public ResponseEntity<Object> getGroupInfo(@Valid @RequestBody GroupInfoRequest groupInfoRequest) {

        return new ResponseEntity<>(camperService.getGroupCampers(groupInfoRequest.getGroup()), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/assignGroup")
    public ResponseEntity<Object> assignManualCamper(@Valid @RequestBody GroupAssignRequest groupAssignRequest) {
        // System.out.println(groupAssignRequest.getId());
        // System.out.println(groupAssignRequest.getGroup());
        Optional<Camper> camper = camperRepository.findById( groupAssignRequest.getId()) ;
        Camper realCamper = camper.get();
        realCamper.setGroup(groupAssignRequest.getGroup());
        // System.out.println(realCamper.getGroup());
        camperService.createCamper(realCamper);
        return new ResponseEntity<>("Camper group has been assigned successfully", HttpStatus.OK);
    }

    public void assignGroup(Camper camper, int grade){
        if (grade < 2){
            camper.setGroup("dates1");
        }
        else if ( 1 < grade && grade < 4){
            camper.setGroup("coconuts1");
        }
        else if ( 3 < grade && grade < 6){
            camper.setGroup("trees1");
        }
        else if ( 5 < grade && grade < 9){
            camper.setGroup("leaders1");
        }
    }
}