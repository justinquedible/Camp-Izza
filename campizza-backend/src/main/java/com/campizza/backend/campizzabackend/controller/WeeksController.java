/*
     CONTROLLERS ARE RESPONSIBLE FOR CONTROLLING THE MAPPING OF CRUD OPERATIONS

             Note: We should try our best to avoid injecting beans with the @Autowired annotation iF possible.
             It allows dependencies to be injected implicitly which seems fast and easy and magical.
             HOWEVER field injection is not recommended.
             Apparently it violates a lot of programming principles, so I guess we should be cautious with it.

             Also, the method calls that directly call our data layer is what would be implemented in the service layer
*/
package com.campizza.backend.campizzabackend.controller;


import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.service.RegisteredWeeksService;
import com.campizza.backend.campizzabackend.service.WeeksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminAPI/weeks")
public class WeeksController {
    @Autowired
    WeeksService weeksService;

    @Autowired
    RegisteredWeeksService regProgramService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllWeeks() {
        return new ResponseEntity<>(weeksService.getWeeksAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addWeeks(@RequestBody Weeks weeks) {
        weeksService.createWeeks(weeks);
        return new ResponseEntity<>("Weeks has been added successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/addCamper/{time}/{camperID}/{weekID}")
    public ResponseEntity<Object> addCamper(@PathVariable("camperID") Long camperID, @PathVariable("weekID") Long weekID, @PathVariable("time") String time) {
        if (time.equals("am")) {
            weeksService.addCamperAM(camperID,weekID);
        }
        else if (time.equals("pm")) {
            weeksService.addCamperPM(camperID,weekID);
        }
        else if (time.equals("pmearly")) {
            weeksService.addCamperPM(camperID,weekID);
        }
        else if (time.equals("amearly")) {
            weeksService.addCamperPM(camperID,weekID);
        }

        else {
            return new ResponseEntity<>("error: invalid time given", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Camper has been added successfully to week id: " + weekID, HttpStatus.CREATED);
    }

}

