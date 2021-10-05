package com.campizza.backend.campizzabackend.controller;
import com.campizza.backend.campizzabackend.model.Counselor;
import com.campizza.backend.campizzabackend.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counselors")
public class CounselorController {

    @Autowired
    private CounselorService counselorService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCounselors() {
        return new ResponseEntity<>(counselorService.getCounselorsAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCounselor(@RequestBody Counselor counselor) {
        counselorService.createCounselor(counselor);
        return new ResponseEntity<>("Counselor has been added successfully!", HttpStatus.CREATED);
    }
}