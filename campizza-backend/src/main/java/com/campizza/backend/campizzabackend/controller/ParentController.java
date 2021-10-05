package com.campizza.backend.campizzabackend.controller;

import com.campizza.backend.campizzabackend.data.ParentRepository;
import com.campizza.backend.campizzabackend.model.HouseHold;
import com.campizza.backend.campizzabackend.model.User;
import com.campizza.backend.campizzabackend.security.payloads.*;
import com.campizza.backend.campizzabackend.service.ParentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.campizza.backend.campizzabackend.data.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;


@RestController
@RequestMapping("/api/parents")
public class ParentController {
    @Autowired
    private ParentService parentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParentRepository parentRepository;

    @CrossOrigin
    @GetMapping("/all")
    //@PreAuthorize("hasRole('adminRole') or hasRole('counselorRole') or hasRole('parentRole')")
    public ResponseEntity<Object> getAllParents() {
        return new ResponseEntity<>(parentService.getParentsAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/getInfo")
    public ResponseEntity<Object> getHouseHoldInfo(@Valid @RequestBody ParentInfoRequest parentInfoRequest) {

        if (userRepository.existsByEmail(parentInfoRequest.getParentEmail()) == false) {

            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email was not found"));
        }


        Optional<User> user = userRepository.findByEmail(parentInfoRequest.getParentEmail());
        User realUser = user.get();
        //System.out.println(realUser.getEmail());

        if (parentRepository.existsByUser(realUser) == true){

            Optional<HouseHold> parent = parentRepository.findByUser(realUser);
            HouseHold realParent = parent.get();

            return ResponseEntity.ok(new ParentInfoResponse(realParent));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: HouseHold Info was not found"));
    }

    @CrossOrigin
    @PostMapping("/add")
   // @PreAuthorize("hasRole('adminRole') or hasRole('counselorRole') or hasRole('parentRole')")
    public ResponseEntity<Object> addParent (@Valid @RequestBody ParentRequest parentRequest) {


        if (userRepository.existsByEmail(parentRequest.getParentEmail()) == false) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email was not found"));
        }


        Optional<User> user = userRepository.findByEmail(parentRequest.getParentEmail());
        User realUser = user.get();

        if (parentRepository.existsByUser(realUser) == true){
            Optional<HouseHold> parent = parentRepository.findByUser(realUser);
            HouseHold realParent = parent.get();
            realParent.updateInfo(parentRequest);

            parentService.createParent(realParent);
            return new ResponseEntity<>("Parent has been added successfully!", HttpStatus.CREATED);
        }

        HouseHold parent = new HouseHold(parentRequest, realUser);

        parentService.createParent(parent);
        return new ResponseEntity<>("Parent has been added successfully!", HttpStatus.CREATED);
    }




}
