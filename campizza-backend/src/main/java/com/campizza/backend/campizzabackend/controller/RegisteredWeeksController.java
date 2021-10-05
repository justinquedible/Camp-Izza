/*
     CONTROLLERS ARE RESPONSIBLE FOR CONTROLLING THE MAPPING OF CRUD OPERATIONS

             Note: We should try our best to avoid injecting beans with the @Autowired annotation iF possible.
             It allows dependencies to be injected implicitly which seems fast and easy and magical.
             HOWEVER field injection is not recommended.
             Apparently it violates a lot of programming principles, so I guess we should be cautious with it.

             Also, the method calls that directly call our data layer is what would be implemented in the service layer
*/
package com.campizza.backend.campizzabackend.controller;


import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.data.ParentRepository;
import com.campizza.backend.campizzabackend.data.WeeksRepository;
import com.campizza.backend.campizzabackend.model.*;
import com.campizza.backend.campizzabackend.security.payloads.*;
import com.campizza.backend.campizzabackend.service.RegisteredWeeksService;
import com.campizza.backend.campizzabackend.service.WeeksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/adminAPI/registeredProgram")
public class RegisteredWeeksController {
    @Autowired
    RegisteredWeeksService registeredWeeksService;

    @Autowired
    WeeksService weeksService;

    @Autowired
    WeeksRepository weeksRepository;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    CamperRepository camperRepository;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRegisteredPrograms() {
        return new ResponseEntity<>(registeredWeeksService.getRegisteredWeeksAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addRegisteredProgram(@RequestBody RegisteredWeeks registeredWeeks) {
        registeredWeeksService.createRegisteredWeeks(registeredWeeks);
        return new ResponseEntity<>("Program has been added successfully!", HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/addWeek/{program}")
    public ResponseEntity<Object> addWeeks(@PathVariable("program") Long program, @RequestBody Weeks weeks) {
        weeksRepository.saveAndFlush(weeks);

        registeredWeeksService.createWeeks(program, weeks);
        return new ResponseEntity<>("week has been added successfully!", HttpStatus.CREATED);
    }



    @CrossOrigin
    @PostMapping("/editWeeks/{year}")
    public ResponseEntity<Object> allWeeks(@PathVariable("year") int currentYear, @RequestBody SessionEditWeekRequest request) throws ParseException {
        List weeksEdited = request.getAllWeeks();
        registeredWeeksService.setCutoff(request, currentYear);
        for (int i = 0; i < weeksEdited.size(); i++) {

            List week = (List) weeksEdited.get(i);

            String start = (String) week.get(0);
            String end = (String) week.get(1);
            int weekId = (int) week.get(2);
            Long longWeekID = (long) weekId;
            if (weekId != 0){
                registeredWeeksService.editWeek(longWeekID, start, end);
            }
            if (weekId == 0 && start != ""){
                SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
                Date starts = formatter1.parse(start);
                Date ends = formatter1.parse(end);
                Weeks weeks = new Weeks(starts, ends);
                weeksRepository.saveAndFlush(weeks);

                registeredWeeksService.createWeeksYear(currentYear, weeks);
            }


        }

        return new ResponseEntity<>("Weeks edited", HttpStatus.OK);
    }


    @PostMapping("/addWeekID/{weekID}/{program}")
    public ResponseEntity<Object> addWeeks(@PathVariable("program") Long program, @PathVariable("weekID") Long weekID) {
        Weeks week = weeksRepository.findById(weekID).get();

        registeredWeeksService.createWeeks(program, week);
        return new ResponseEntity<>("week has been added successfully!", HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("/allWeeks/{program}")
    public ResponseEntity<Object> allWeeks(@PathVariable("program") Long program) {


        return new ResponseEntity<>(registeredWeeksService.getProgramWeeks(program), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/allWeeksYear/{program}")
    public ResponseEntity<Object> allWeeks(@PathVariable("program") int year ) {


        return new ResponseEntity<>(registeredWeeksService.getWeeksAll(year), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/allWeeksYearCutoff/{program}")
    public ResponseEntity<Object> allWeeksCutoff(@PathVariable("program") int year ) {


        return new ResponseEntity<>(registeredWeeksService.getProgramInfo(year), HttpStatus.OK);
    }

//    @GetMapping("program/{program}")
//    public ResponseEntity<Object> deleteCamper(@PathVariable("program") Long program) {
//        return new ResponseEntity<>(registeredWeeksService.getWeeksAll(), HttpStatus.OK);
//    }
    @CrossOrigin
    @PostMapping("/getCamperWeeks/{year}")
    public ResponseEntity<Object> getCamperWeeks(@PathVariable("year") int currentYear, @RequestBody @Valid CamperInfoRequest request) {
        List<MangeCamperResponse> enrolled = new ArrayList<MangeCamperResponse>();

        Optional<Camper> campers = camperRepository.findById(request.getCamperID());
        Camper camper = campers.get();

        // Have Camper Info
        System.out.println("In weeks");
        System.out.println(camper.getId());
        System.out.println(camper.getUser());
        System.out.println(camper.getUser().getFirstName());
        Optional<HouseHold> parent = parentRepository.findByUser(camper.getUser());
        System.out.println(parent);

        // Schedule Info
        List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(currentYear);
        List<camperWeeks> weeksRegistered = new ArrayList<camperWeeks>(); // Weeks Contained here

        for (int i = 0; i < programWeeks.size(); i++){
            Weeks week = programWeeks.get(i);
            if (week.getCampersAM().contains(camper) &&  week.getCampersPM().contains(camper) ){
                camperWeeks campWeek = new camperWeeks(week,"full",i+1);
                weeksRegistered.add(campWeek);
            }
            else if (week.getCampersPM().contains(camper)){
                camperWeeks campWeek = new camperWeeks(week,"pm",i+1);
                weeksRegistered.add(campWeek);
            }
            else if (week.getCampersAM().contains(camper)){
                camperWeeks campWeek = new camperWeeks(week,"am",i+1);
                weeksRegistered.add(campWeek);
            }
            else{
                camperWeeks campWeek = new camperWeeks(week,"not-reg",i+1);
                weeksRegistered.add(campWeek);
            }
        }
        // End Schedule Info
        HouseHold realParent;

        if (parent.isPresent() != false){
            realParent = parent.get();
            MangeCamperResponse camperInfo = new MangeCamperResponse(camper, realParent, weeksRegistered);
            enrolled.add(camperInfo);
        }
        else{
            MangeCamperResponse camperInfo = new MangeCamperResponse(camper, "", weeksRegistered);
            enrolled.add(camperInfo);
        }



        return  ResponseEntity.ok(weeksRegistered);

    }
    @CrossOrigin
    @GetMapping("/getCampers/{year}")
    public ResponseEntity<Object> getAllCampersInProgram(@PathVariable("year") int currentYear) {
        //registeredWeeksService.getRegisteredWeeksAll();
        List<MangeCamperResponse> enrolled = new ArrayList<MangeCamperResponse>();
        Set<Camper> campers = registeredWeeksService.getCampersInProgramYear(currentYear);

        for (Camper camper: campers) {
            // Have Camper Info
            System.out.println("In weeks");
            System.out.println(camper.getId());
            System.out.println(camper.getUser());
            System.out.println(camper.getUser().getFirstName());
            Optional<HouseHold> parent = parentRepository.findByUser(camper.getUser());
            System.out.println(parent);

            // Schedule Info
            List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(currentYear);
            List<camperWeeks> weeksRegistered = new ArrayList<camperWeeks>(); // Weeks Contained here

            for (int i = 0; i < programWeeks.size(); i++){
                Weeks week = programWeeks.get(i);
                if (week.getCampersAM().contains(camper) &&  week.getCampersPM().contains(camper) ){
                    camperWeeks campWeek = new camperWeeks(week,"full",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersPM().contains(camper)){
                    camperWeeks campWeek = new camperWeeks(week,"pm",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersAM().contains(camper)){
                    camperWeeks campWeek = new camperWeeks(week,"am",i+1);
                    weeksRegistered.add(campWeek);
                }
                else{
                    camperWeeks campWeek = new camperWeeks(week,"not-reg",i+1);
                    weeksRegistered.add(campWeek);
                }
            }
            // End Schedule Info
            HouseHold realParent;

            if (parent.isPresent() != false){
                realParent = parent.get();
                MangeCamperResponse camperInfo = new MangeCamperResponse(camper, realParent, weeksRegistered);
                enrolled.add(camperInfo);
            }
            else{
                MangeCamperResponse camperInfo = new MangeCamperResponse(camper, "", weeksRegistered);
                enrolled.add(camperInfo);
            }

        }

        return  ResponseEntity.ok(enrolled);
    }
    @CrossOrigin
    @GetMapping("/getAllCampers/")
    public ResponseEntity<Object> getAllCampers() {
        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);
        System.out.println("All campers");
        System.out.println(currentYear);

        //registeredWeeksService.getRegisteredWeeksAll();
        List<MangeCamperResponse> enrolled = new ArrayList<MangeCamperResponse>();
        Set<Camper> campers = registeredWeeksService.getCampersInAllPrograms();
        for (Camper camper: campers) {
            // Have Camper Info
            System.out.println("In weeks");
            System.out.println(camper.getUser());
            System.out.println(camper.getUser().getFirstName());
            Optional<HouseHold> parent = parentRepository.findByUser(camper.getUser());
            System.out.println(parent);
            //HouseHold realParent = parent.get(); // HouseHold Info
            // Schedule Info
            List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(currentYear);
            List<camperWeeks> weeksRegistered = new ArrayList<camperWeeks>(); // Weeks Contained here

            for (int i = 0; i < programWeeks.size(); i++){
                Weeks week = programWeeks.get(i);
                if (week.getCampersAM().contains(camper) &&  week.getCampersPM().contains(camper) ){
                    camperWeeks campWeek = new camperWeeks(week,"full",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersPM().contains(camper)){
                    camperWeeks campWeek = new camperWeeks(week,"pm",i+1);
                    weeksRegistered.add(campWeek);
                }
                else if (week.getCampersAM().contains(camper)){
                    camperWeeks campWeek = new camperWeeks(week,"am",i+1);
                    weeksRegistered.add(campWeek);
                }
                else{
                    camperWeeks campWeek = new camperWeeks(week,"not-reg",i+1);
                    weeksRegistered.add(campWeek);
                }
            }
            HouseHold realParent;
            // End Schedule Info
            if (parent.isPresent() != false){
                realParent = parent.get();
                MangeCamperResponse camperInfo = new MangeCamperResponse(camper, realParent, weeksRegistered);
                enrolled.add(camperInfo);
            }
            else{
                MangeCamperResponse camperInfo = new MangeCamperResponse(camper, "", weeksRegistered);
                enrolled.add(camperInfo);
            }


        }

        return  ResponseEntity.ok(enrolled);
    }

    @CrossOrigin
    @GetMapping("/getYearGroupLimits/")
    public ResponseEntity<Object> getYearGroupLimits() {
        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);


        return new ResponseEntity<>(registeredWeeksService.getGroupLimits(currentYear), HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("/setYearGroupLimits/")
    public ResponseEntity<Object> setYearGroupLimits(@Valid @RequestBody SessionLimitRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);
        registeredWeeksService.alterGroupLimit(request);

        return ResponseEntity.ok(new MessageResponse("Groups Limit changed successfully!"));

    }

    @CrossOrigin
    @GetMapping("/getYearPricing/{year}")
    public ResponseEntity<Object> getYearPricing(@PathVariable("year") int currentYear) {


        return new ResponseEntity<>(registeredWeeksService.getSessionPrice(currentYear), HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("/setYearPricing/")
    public ResponseEntity<Object> setYearPricing(@Valid @RequestBody SessionPriceRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);
        registeredWeeksService.setSessionPrice(request);

        return ResponseEntity.ok(new MessageResponse("Groups Limit changed successfully!"));

    }

    @CrossOrigin
    @GetMapping("/getWeekHolidays/{weekID}")
    public ResponseEntity<Object> getHolidays(@PathVariable("weekID") Long weekID) {


        return new ResponseEntity<>(registeredWeeksService.getHolidays(weekID), HttpStatus.OK);


    }

    @CrossOrigin
    @PostMapping("/setWeekHolidays/{weekID}")
    public ResponseEntity<Object> setHolidays(@PathVariable("weekID") Long weekID, @Valid @RequestBody HolidayEditRequest request) throws ParseException {

        registeredWeeksService.setHolidays(weekID, request.getHoliday());

        return new ResponseEntity<>("Weeks edited", HttpStatus.OK);


    }

    @CrossOrigin
    @PostMapping("/removeWeekHolidays/{weekID}")
    public ResponseEntity<Object> removeHolidays(@PathVariable("weekID") Long weekID, @Valid @RequestBody HolidayEditRequest request) throws ParseException {

        registeredWeeksService.removeHolidays(weekID, request.getHolidayID());

        return new ResponseEntity<>("Weeks edited", HttpStatus.OK);


    }
}
