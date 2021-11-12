package com.campizza.backend.campizzabackend.controller;
import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.model.Attendance;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.security.payloads.AttendanceRequest;
import com.campizza.backend.campizzabackend.security.payloads.AttendanceResponse;
import com.campizza.backend.campizzabackend.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private WeeksService weeksService;

    @Autowired
    private CamperRepository camperRepository;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Object> getAllAttendances() {
        return new ResponseEntity<>(attendanceService.getAttendancesAll(), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Object> addAttendance(@RequestBody Attendance attendance) {
        attendanceService.createAttendance(attendance);
        return new ResponseEntity<>("Attendance has been added successfully!", HttpStatus.CREATED);
    }
    @CrossOrigin
    @PostMapping("/week/{weekID}/addAttendanceDate/")
    public ResponseEntity<Object> addNewAttendanceWithWeek(@RequestBody Attendance attendance, @PathVariable("weekID") Long weekID) {
        attendanceService.createAttendance(attendance);
        weeksService.addAttendanceDate(attendance.getId(), weekID);

        return new ResponseEntity<>("Attendance has been added successfully and connected with week!", HttpStatus.CREATED);
    }

    // @TODO: add check if record doesnt exist yet
    @CrossOrigin
    @PostMapping("/addCamper/{camperID}")
    public ResponseEntity<Object> addAttendance(@PathVariable("camperID") Long camperID, @RequestBody AttendanceRequest attendanceRequest) {
        Optional<Camper> c = camperRepository.findById(camperID);
        Camper camper = c.get();
        attendanceService.addCamper(camper, attendanceRequest.getDate());
        // System.out.println("DATE: " + attendanceRequest.getDate());
        return new ResponseEntity<>("Attendance has been updated successfully!", HttpStatus.CREATED);
    }


    // Attendance based on Date object HARD CODED FOR GROUP NAME @TODO: potentially update later if time
    @CrossOrigin
    @GetMapping("/getAttendance")
    @ResponseBody
    public ResponseEntity<Object> getAttendanceDateRecord(@Valid @RequestParam String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        AttendanceResponse response = new AttendanceResponse();
        String[] groupNames = {"dates1", "dates2", "coconuts1", "coconuts2", "trees1", "trees2", "leaders1", "leaders2"};
        for (String group : groupNames) {
            // System.out.println(group);

            // Build the response Json back for frontend parsing
            response.addExpectedAMCamperRecord(group, attendanceService.getExpectedAMAttendance(d, group)); // get expected am attendance
            response.addExpectedPMCamperRecord(group, attendanceService.getExpectedPMAttendance(d, group)); // get expected pm attendance

            response.updatePresentCurrentDateAMCamperRecord(group, attendanceService.getActualAMAttendance(d, group)); // get actual attendance
            response.updatePresentCurrentDatePMCamperRecord(group, attendanceService.getActualPMAttendance(d, group)); // get actual attendance

            response.updatePresentCurrentDateBonusCamperRecord(group, attendanceService.getBonusCamper(d, group, "am"), "AM");
            response.updatePresentCurrentDateBonusCamperRecord(group, attendanceService.getBonusCamper(d, group, "pm"), "PM");
            response.updatePresentCurrentDateBonusCamperRecord(group, attendanceService.getBonusCamper(d, group, "full"), "Full");
        }
        response.setDate(d);
        return ResponseEntity.ok(response);
    }
    @CrossOrigin
    @PostMapping("/updateAttendance")
    public ResponseEntity<Object> editAttendanceRecord(@Valid @RequestBody AttendanceRequest attendanceRequest) throws InterruptedException {
        attendanceService.updateCamper(attendanceRequest.getCamperID(), attendanceRequest.getDate(), attendanceRequest.getTime());
        return new ResponseEntity<>("Attendance has been updated!", HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/updateBonusCamperStringAttendance")
    public ResponseEntity<Object> editBonusAttendanceRecord(@Valid @RequestBody AttendanceRequest attendanceRequest) throws InterruptedException {
        attendanceService.updateBonusCamper(attendanceRequest.getBonusCamperName(), attendanceRequest.getBonusCamperGroup(), attendanceRequest.getDate(), attendanceRequest.getTime());
        return new ResponseEntity<>("Attendance for Bonus Camper has been updated!", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getAttendanceReport/{camperID}/{year}")
    public ResponseEntity<Object> getAttendanceReport(@PathVariable("camperID") Long camperID, @PathVariable("year") Integer year) {
        attendanceService.getCamperAttendanceByProgramYear(camperID, year);

        return new ResponseEntity<>(attendanceService.getCamperAttendanceByProgramYear(camperID, year), HttpStatus.OK);

    }

}