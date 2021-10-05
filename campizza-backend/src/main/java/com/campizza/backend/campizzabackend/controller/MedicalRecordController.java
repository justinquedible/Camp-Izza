package com.campizza.backend.campizzabackend.controller;

import java.util.Optional;
import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.data.MedicalRecordRepository;
import com.campizza.backend.campizzabackend.data.UserRepository;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.MedicalRecord;
import com.campizza.backend.campizzabackend.model.User;
import com.campizza.backend.campizzabackend.security.payloads.MedicalRecordRequest;
import com.campizza.backend.campizzabackend.security.payloads.MessageResponse;
import com.campizza.backend.campizzabackend.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {
    @Autowired

    MedicalRecordService medicalRecordService;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    CamperRepository camperRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMedicalRecords()
    {
        return new ResponseEntity<>(medicalRecordService.getMedicalRecordsAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/add")
   public ResponseEntity<Object> addMedicalRecord (@RequestBody MedicalRecord medicalRecord) {

        medicalRecordService.createMedicalRecord(medicalRecord);
        return new ResponseEntity<>("Medical Recrod has been added succesfully!", HttpStatus.CREATED);
    }
}
