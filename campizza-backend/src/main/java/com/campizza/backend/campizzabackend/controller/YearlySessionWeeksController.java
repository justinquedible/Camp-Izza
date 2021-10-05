/*
     CONTROLLERS ARE RESPONSIBLE FOR CONTROLLING THE MAPPING OF CRUD OPERATIONS

             Note: We should try our best to avoid injecting beans with the @Autowired annotation iF possible.
             It allows dependencies to be injected implicitly which seems fast and easy and magical.
             HOWEVER field injection is not recommended.
             Apparently it violates a lot of programming principles, so I guess we should be cautious with it.

             Also, the method calls that directly call our data layer is what would be implemented in the service layer
*/
package com.campizza.backend.campizzabackend.controller;


import com.campizza.backend.campizzabackend.model.YearlySessionWeeks;
import com.campizza.backend.campizzabackend.service.YearlySessionWeeksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yearlysessionweeks")
public class YearlySessionWeeksController {
    @Autowired
    YearlySessionWeeksService yearlySessionWeeksService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllYearlySessionWeeks() {
        return new ResponseEntity<>(yearlySessionWeeksService.getYearlySessionWeeksAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addYearlySessionWeeks(@RequestBody YearlySessionWeeks yearlySessionWeeks) {
        yearlySessionWeeksService.createYearlySessionWeeks(yearlySessionWeeks);
        return new ResponseEntity<>("YearlySessionWeeks has been added successfully!", HttpStatus.CREATED);
    }
}

