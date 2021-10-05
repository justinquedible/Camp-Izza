package com.campizza.backend.campizzabackend.controller;
import com.campizza.backend.campizzabackend.model.Group;
import com.campizza.backend.campizzabackend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllGroups() {
        return new ResponseEntity<>(groupService.getGroupsAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addGroup(@RequestBody Group platoon) {
        groupService.createGroup(platoon);
        return new ResponseEntity<>("Group has been added successfully!", HttpStatus.CREATED);
    }

//    @PostMapping("/addCamper")
//    public ResponseEntity<Object> addCamper(@RequestBody Camper camper) {
//        groupService.createCamper(camper);
//        return new ResponseEntity<>("camper has been added successfully!", HttpStatus.CREATED);
//    }

}