/*
     CONTROLLERS ARE RESPONSIBLE FOR CONTROLLING THE MAPPING OF CRUD OPERATIONS

             Note: We should try our best to avoid injecting beans with the @Autowired annotation iF possible.
             It allows dependencies to be injected implicitly which seems fast and easy and magical.
             HOWEVER field injection is not recommended.
             Apparently it violates a lot of programming principles, so I guess we should be cautious with it.

             Also, the method calls that directly call our data layer is what would be implemented in the service layer
*/
package com.campizza.backend.campizzabackend.controller;


import com.campizza.backend.campizzabackend.data.*;
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
@RequestMapping("/adminAPI/roster")
public class RosterController {
    @Autowired
    RegisteredWeeksService registeredWeeksService;

    @Autowired
    CamperRepository camperRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeeksService weeksService;

    @Autowired
    WeeksRepository weeksRepository;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @CrossOrigin
    @GetMapping("/personalCamper")
    public ResponseEntity<Object> getAllCamper() {
        return new ResponseEntity<>(camperRepository.findAllCampersIfExist(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/counselorInfo")
    public ResponseEntity<Object> getAllCounselors() {
        Map<Long, Map<String, String>> campers = new HashMap<>();


        List<User> allCounselors = new ArrayList<>();
        Role role = new Role(3L,currentRole.counselorRole);
        allCounselors.addAll(userRepository.findAllByRolesContaining(role));
        Role roled = new Role(5L,currentRole.counselorArchived);
        allCounselors.addAll(userRepository.findAllByRolesContaining(roled));
        List<String> att = new ArrayList<>();
        att.add("id");att.add("firstName");att.add("lastName");
        att.add("email");att.add("group");att.add("role");




        for (User user : allCounselors) {
            Map<String, String> camperDict = new HashMap<>();
            campers.put(user.getId(), camperDict);
        }

        for (String attribute : att){
            switch (attribute) {
                case "id":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, String.valueOf(user.getId()));
                    }
                    break;
                case "firstName":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, user.getFirstName());
                    }
                    break;
                case "lastName":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, user.getLastName());
                    }
                    break;
                case "email":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, user.getEmail());
                    }
                    break;
                case "group":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, user.getGroup());
                    }
                    break;
                case "role":
                    for (User user : allCounselors) {
                        campers.get(user.getId()).put(attribute, user.getRoles().get(0).getStringRole());
                    }
                    break;
            }

        }

        return new ResponseEntity<>(campers.values(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/parentInfo")
    public ResponseEntity<Object> getAllParents() {
        return new ResponseEntity<>(camperRepository.findAllCampersIfExist(), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/requestInfo")
    public ResponseEntity<Object> infoRequest(@Valid @RequestBody RosterInfoRequest request) throws IllegalAccessException {
        List<String> camperAttributeList = request.getAttributes();
        RosterResponse response = new RosterResponse();

        // Get current programs weeks to keep track of week numbers
        List<Weeks> programWeeks = registeredWeeksService.getWeeksAll(request.getYear());

        for (String attribute : camperAttributeList) {

            // filtering total campers by personal account registered vs just account
            if (attribute.equals("personalaccCreated")) {
                for (Camper camper : camperRepository.findAll()) {
                    response.addCamper(camper.getId());
                }
            } else if (attribute.equals("personalallReg")) {
                response.addCamperList(camperRepository.findAllCampersRegisteredByYear(request.getYear()));
            }

            // filtering total campers by weeks
            else if (attribute.equals("weeksAll")) {
                for (int i = 0; i < programWeeks.size(); i++) {
                    List<Long> resultEarlyAM = camperRepository.findAllAMEarlyCampersRegisteredById(programWeeks.get(i).getWeekId());
                    response.addAttributesList(resultEarlyAM, attribute, "early");
                    if (!request.isWeeksextendedCare()) {
                        List<Long> resultAM = camperRepository.findAllAMCampersRegisteredById(programWeeks.get(i).getWeekId());
                        response.addAttributesList(resultAM, attribute, "regular");
                    }
                    List<Long> resultEarlyPM = camperRepository.findAllPMEarlyCampersRegisteredById(programWeeks.get(i).getWeekId());
                    response.addAttributesList(resultEarlyPM, attribute, "early");
                    if (!request.isWeeksextendedCare()) {
                        List<Long> resultPM = camperRepository.findAllPMCampersRegisteredById(programWeeks.get(i).getWeekId());
                        response.addAttributesList(resultPM, attribute, "regular");
                    }
                }
            }

            // individual weeks
            else if (attribute.startsWith("weekswk") && !request.isWeeksAll()) {

                String tempAttributeTime = attribute.substring(7);
                if (tempAttributeTime.endsWith("AM")) {
                    tempAttributeTime = tempAttributeTime.substring(0, tempAttributeTime.length() - 2);
                    List<Long> resultEarlyAM = camperRepository.findAllAMEarlyCampersRegisteredById(programWeeks.get(Integer.parseInt(tempAttributeTime) - 1).getWeekId());
                    response.addAttributesList(resultEarlyAM, attribute, "early");
                    if (!request.isWeeksextendedCare()) {
                        List<Long> resultAM = (camperRepository.findAllAMCampersRegisteredById(programWeeks.get(Integer.parseInt(tempAttributeTime) - 1).getWeekId()));
                        response.addAttributesList(resultAM, attribute, "regular");
                    }

                } else if (tempAttributeTime.endsWith("PM")) {
                    tempAttributeTime = tempAttributeTime.substring(0, tempAttributeTime.length() - 2);
                    List<Long> resultEarlyPM = camperRepository.findAllPMEarlyCampersRegisteredById(programWeeks.get(Integer.parseInt(tempAttributeTime) - 1).getWeekId());
                    response.addAttributesList(resultEarlyPM, attribute, "early");
                    if (!request.isWeeksextendedCare()) {
                        List<Long> resultPM = (camperRepository.findAllPMCampersRegisteredById(programWeeks.get(Integer.parseInt(tempAttributeTime) - 1).getWeekId()));
                        response.addAttributesList(resultPM, attribute, "regular");
                    }
                }
            }
        }

        // Attributes based on current num campers
        for (String attribute : camperAttributeList) {

            // filtering attributes for each camper by personal info
            if (attribute.startsWith("personal")) {
                switch (attribute) {
                    case "personalchildID":
                        for (Long id : response.campers.keySet()) {
                            response.addAttribute(id, attribute, String.valueOf(id));
                        }
                        break;
                    case "personalfirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getFirstName()));
                        }
                        break;
                    case "personallastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getLastName()));
                        }
                        break;
                    case "personalgender":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getGenderEnum()));
                        }
                        break;
                    case "personaldob":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getDobDate()));
                        }
                        break;
                    case "personalschool":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getSchoolName()));
                        }
                        break;
                    case "personalgrade":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, String.valueOf(camper.getGradeNum())));
                        }
                        break;
                    case "personalshirtSize":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getShirtEnum()));
                        }
                        break;
                    case "personalnumShirts":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, String.valueOf(camper.getNumShirts())));
                        }
                        break;
                }
            }
            // Medical Info
            else if (attribute.startsWith("medicalInfo")) {
                switch (attribute) {
                    case "medicalInfodoctorName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getDoctorName());
                            }
                        }
                        break;

                    case "medicalInfodoctorPhone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getDoctorPhone());
                            }
                        }
                        break;

                    case "medicalInfoinsurance":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getInsurance());
                            }
                        }
                        break;

                    case "medicalInfopolicyholder":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getPolicyHolder());
                            }
                        }
                        break;

                    case "medicalInfoillnesses":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getIllnesses());
                            }
                        }
                        break;

                    case "medicalInfoallergiesDiet":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getAllergy_names());
                            }
                        }
                        break;

                    case "medicalInfomedications":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getMedicationNames());
                            }
                        }
                        break;

                    case "medicalInfoactivities":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getActivitiesName());
                            }
                        }
                        break;

                    case "medicalInfotreatments":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getMedicalTreatmentsNames());
                            }
                        }
                        break;

                    case "medicalInfoimmunizations":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getImmunizations());
                            }
                        }
                        break;

                    case "medicalInfotetanus":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getTetanusDate());
                            }
                        }
                        break;

                    case "medicalInfocomments":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, c.get().getComment()));
                        }
                        break;
                }
            } else if (attribute.startsWith("parent")) {
                switch (attribute) {
                    case "parentparentID":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, String.valueOf(c.get().getParent().getId()));
                            }
                        }
                        break;

                    case "parentg1FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameFirst1());
                            }
                        }
                        break;

                    case "parentg1LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameLast1());
                            }
                        }
                        break;

                    case "parentg2FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameFirst2());
                            }
                        }
                        break;

                    case "parentg2LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameLast2());
                            }
                        }
                        break;

                    case "parentaddress1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getAddress1());
                            }
                        }
                        break;

                    case "parentaddress2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getAddress2());
                            }
                        }
                        break;

                    case "parentcountry":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getCountry());
                            }
                        }
                        break;

                    case "parentcity":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getCity());
                            }
                        }
                        break;

                    case "parentstate":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getState());
                            }
                        }
                        break;

                    case "parentzipcode":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getZipPostalCode());
                            }
                        }
                        break;

                    case "parentemail1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianEmail1());
                            }
                        }
                        break;

                    case "parentemail2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianEmail2());
                            }
                        }
                        break;

                    case "parentg1Phone1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian1Phone1());
                            }
                        }
                        break;

                    case "parentg1Phone2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian1Phone2());
                            }
                        }
                        break;

                    case "parentg2Phone1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian2Phone1());
                            }
                        }
                        break;

                    case "parentg2Phone2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian2Phone2());
                            }
                        }
                        break;

                }
            }
            else if(attribute.startsWith("emergency")) {
                switch (attribute) {
                    case "emergency1FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1first());
                            }
                        }
                        break;
                    case "emergency1LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1last());
                            }
                        }
                        break;

                    case "emergency1Relationship":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1relationship());
                            }
                        }
                        break;

                    case "emergency1Phone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1Phone());
                            }
                        }
                        break;

                    case "emergency1Authorized":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1authorized());
                            }
                        }
                        break;

                    case "emergency2FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2first());
                            }
                        }
                        break;
                    case "emergency2LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2last());
                            }
                        }
                        break;

                    case "emergency2Relationship":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2relationship());
                            }
                        }
                        break;

                    case "emergency2Phone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2Phone());
                            }
                        }
                        break;

                    case "emergency2Authorized":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2authorized());
                            }
                        }
                        break;


                }
            }

            else if(attribute.startsWith("payments")) {
                switch (attribute) {
                    case "paymentsamountPaid":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() ) {

                                response.addAttribute(id, attribute,String.valueOf(c.get().getPaid()));
                            }
                        }
                        break;
                    case "paymentscredit":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() ) {

                                response.addAttribute(id, attribute, String.valueOf(c.get().getCredit()));
                            }
                        }
                        break;

//                    case "paymentsearlyBird":
//                        for (Long id : response.campers.keySet()) {
//                            Optional<Camper> c = camperRepository.findById(id);
//                            if (c.isPresent() && c.get().getParent() != null) {
//                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1relationship());
//                            }
//                        }
//                        break;

                }
            }

        }
        return new ResponseEntity<>(response.campers.values(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/emergencyInfo")
    public ResponseEntity<Object> emergencyInfo(@Valid @RequestBody EmergencyReleaseRequest request) throws IllegalAccessException {
        List<String> camperAttributeList = request.getAttributes();
        RosterResponse response = new RosterResponse();
        System.out.println("HUHUHHUH");
        System.out.println(request.getAttributes());

        response.addCamper(request.getCamperId());

        // Attributes based on current num campers
        for (String attribute : camperAttributeList) {

            // filtering attributes for each camper by personal info
            if (attribute.startsWith("personal")) {
                switch (attribute) {
                    case "personalchildID":
                        for (Long id : response.campers.keySet()) {
                            response.addAttribute(id, attribute, String.valueOf(id));
                        }
                        break;
                    case "personalfirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getFirstName()));
                        }
                        break;
                    case "personallastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getLastName()));
                        }
                        break;
                    case "personalgender":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getGenderEnum()));
                        }
                        break;
                    case "personaldob":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getDobDate()));
                        }
                        break;
                    case "personalschool":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getSchoolName()));
                        }
                        break;
                    case "personalgrade":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, String.valueOf(camper.getGradeNum())));
                        }
                        break;
                    case "personalshirtSize":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, camper.getShirtEnum()));
                        }
                        break;
                    case "personalnumShirts":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, String.valueOf(camper.getNumShirts())));
                        }
                        break;
                }
            }
            // Medical Info
            else if (attribute.startsWith("medicalInfo")) {
                switch (attribute) {
                    case "medicalInfodoctorName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getDoctorName());
                            }
                        }
                        break;

                    case "medicalInfodoctorPhone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getDoctorPhone());
                            }
                        }
                        break;

                    case "medicalInfoinsurance":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getInsurance());
                            }
                        }
                        break;

                    case "medicalInfopolicyholder":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getPolicyHolder());
                            }
                        }
                        break;

                    case "medicalInfoillnesses":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getIllnesses());
                            }
                        }
                        break;

                    case "medicalInfoallergiesDiet":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getAllergy_names());
                            }
                        }
                        break;

                    case "medicalInfomedications":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getMedicationNames());
                            }
                        }
                        break;

                    case "medicalInfoactivities":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getActivitiesName());
                            }
                        }
                        break;

                    case "medicalInfotreatments":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getMedicalTreatmentsNames());
                            }
                        }
                        break;

                    case "medicalInfoimmunizations":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getImmunizations());
                            }
                        }
                        break;

                    case "medicalInfotetanus":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getMedicalRecord() != null) {
                                response.addAttribute(id, attribute, c.get().getMedicalRecord().getTetanusDate());
                            }
                        }
                        break;

                    case "medicalInfocomments":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            c.ifPresent(camper -> response.addAttribute(id, attribute, c.get().getComment()));
                        }
                        break;
                }
            } else if (attribute.startsWith("parent")) {
                switch (attribute) {
                    case "parentparentID":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, String.valueOf(c.get().getParent().getId()));
                            }
                        }
                        break;

                    case "parentg1FirstName":
                        System.out.println("hit");
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            System.out.println(c.get().getFirstName());
                            System.out.println(c.get().getParent());
                            if (c.isPresent() && c.get().getParent() != null) {
                                System.out.println("double hit");
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameFirst1());
                            }
                        }
                        break;

                    case "parentg1LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameLast1());
                            }
                        }
                        break;

                    case "parentg2FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameFirst2());
                            }
                        }
                        break;

                    case "parentg2LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianNameLast2());
                            }
                        }
                        break;

                    case "parentaddress1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getAddress1());
                            }
                        }
                        break;

                    case "parentaddress2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getAddress2());
                            }
                        }
                        break;

                    case "parentcountry":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getCountry());
                            }
                        }
                        break;

                    case "parentcity":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getCity());
                            }
                        }
                        break;

                    case "parentstate":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getState());
                            }
                        }
                        break;

                    case "parentzipcode":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getZipPostalCode());
                            }
                        }
                        break;

                    case "parentemail1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianEmail1());
                            }
                        }
                        break;

                    case "parentemail2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardianEmail2());
                            }
                        }
                        break;

                    case "parentg1Phone1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian1Phone1());
                            }
                        }
                        break;

                    case "parentg1Phone2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian1Phone2());
                            }
                        }
                        break;

                    case "parentg2Phone1":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian2Phone1());
                            }
                        }
                        break;

                    case "parentg2Phone2":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getGuardian2Phone2());
                            }
                        }
                        break;

                }
            }
            else if(attribute.startsWith("emergency")) {
                switch (attribute) {
                    case "emergencye1FirstName":
                        System.out.println("emo hit");
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                System.out.println("double emo hit");
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1first());
                            }
                        }
                        break;
                    case "emergencye1LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1last());
                            }
                        }
                        break;

                    case "emergencye1Relationship":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1relationship());
                            }
                        }
                        break;

                    case "emergencye1Phone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1Phone());
                            }
                        }
                        break;

                    case "emergencye1Authorized":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency1authorized());
                            }
                        }
                        break;

                    case "emergencye2FirstName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2first());
                            }
                        }
                        break;
                    case "emergencye2LastName":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2last());
                            }
                        }
                        break;

                    case "emergencye2Relationship":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2relationship());
                            }
                        }
                        break;

                    case "emergencye2Phone":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2Phone());
                            }
                        }
                        break;

                    case "emergencye2Authorized":
                        for (Long id : response.campers.keySet()) {
                            Optional<Camper> c = camperRepository.findById(id);
                            if (c.isPresent() && c.get().getParent() != null) {
                                response.addAttribute(id, attribute, c.get().getParent().getEmergency2authorized());
                            }
                        }
                        break;

                }
            }



        }

        return new ResponseEntity<>(response.campers.values(), HttpStatus.OK);
    }
}


