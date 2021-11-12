/*
     CONTROLLERS ARE RESPONSIBLE FOR CONTROLLING THE MAPPING OF CRUD OPERATIONS

             Note: We should try our best to avoid injecting beans with the @Autowired annotation iF possible.
             It allows dependencies to be injected implicitly which seems fast and easy and magical.
             HOWEVER field injection is not recommended.
             Apparently it violates a lot of programming principles, so I guess we should be cautious with it.

             Also, the method calls that directly call our data layer is what would be implemented in the service layer
*/
package com.campizza.backend.campizzabackend.controller;


import com.campizza.backend.campizzabackend.data.PasswordTokenRepository;

import com.campizza.backend.campizzabackend.model.PasswordToken;
import com.campizza.backend.campizzabackend.model.currentRole;
import com.campizza.backend.campizzabackend.security.payloads.*;
import com.campizza.backend.campizzabackend.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.campizza.backend.campizzabackend.model.User;
import com.campizza.backend.campizzabackend.model.Role;

import com.campizza.backend.campizzabackend.data.UserRepository;
import com.campizza.backend.campizzabackend.data.RoleRepository;

import com.campizza.backend.campizzabackend.security.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ResetUtils resetUtils;

    @Autowired
    SendEmail sending;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticator(@Valid @RequestBody LoginRequest loginRequest) {

        //System.out.println(loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        // System.out.println("in signin");
        // System.out.println(roles);
        for (int i = 0; i < roles.size(); i++){
            // System.out.println(roles.get(i));
        }
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(),
                userDetails.getLastName(), userDetails.getEmail(),userDetails.getGroup(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // System.out.println("in signup");
        // System.out.println(signupRequest.getRoles());
        User user = new User(signupRequest.getFirstName(), signupRequest.getLastName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        List<Role> roles = new ArrayList<>();

        if (signupRequest.getRoles() == null) { // default to parent if not specified
            Role parent = roleRepository.findByRoleName(currentRole.parentRole)
                    .orElseThrow(() -> new RuntimeException("Error: Parent Role is not found."));
            roles.add(parent);
        } else {
            for (String role : signupRequest.getRoles()) {
                if (role.equals("adminRole")) {
                    Role admin = roleRepository.findByRoleName(currentRole.adminRole)
                            .orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));

                    roles.add(admin);
                } else if (role.equals("counselorRole")) {
                    Role counselor = roleRepository.findByRoleName(currentRole.counselorRole)
                            .orElseThrow(() -> new RuntimeException("Error: Counselor Role is not found."));

                    roles.add(counselor);
                } else if (role.equals("parentRole")) {
                    Role parent = roleRepository.findByRoleName(currentRole.parentRole)
                            .orElseThrow(() -> new RuntimeException("Error: Parent Role is not found."));

                    roles.add(parent);
                } else if (role.equals("counselorPending")) {
                    Role counselorPending = roleRepository.findByRoleName(currentRole.counselorPending)
                            .orElseThrow(() -> new RuntimeException("Error: Counselor Pending Role is not found."));

                    roles.add(counselorPending);
                } else if (role.equals("counselorArchived")) {
                    Role counselorArchived = roleRepository.findByRoleName(currentRole.counselorArchived)
                            .orElseThrow(() -> new RuntimeException("Error: Counselor Archived Role is not found."));

                    roles.add(counselorArchived);
                } else {
                    throw new RuntimeException("Error: not an approved role");
                }
            }
        }


        user.setRoles(roles);

        userRepository.saveAndFlush(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotRequest forgotRequest) {

        if (userRepository.existsByEmail(forgotRequest.getEmail()) == false) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email was not found"));
        }

        Optional<User> user = userRepository.findByEmail(forgotRequest.getEmail());
        User realUser = user.get();
        String token = ResetUtils.generateJwtToken(forgotRequest.getEmail());
        PasswordToken passwordToken = new PasswordToken(token, realUser);
        passwordTokenRepository.saveAndFlush(passwordToken);
        //SendEmail sending = new SendEmail(passwordToken, realUser);

        sending.sendMail(passwordToken, realUser);
        return ResponseEntity.ok(new MessageResponse("Reset Password email sent successfully!"));
    }

    @PostMapping("/confirm-reset")
    public ResponseEntity<?> validateReset(@Valid @RequestBody ResetRequest resetRequest) {

        Optional<PasswordToken> token = passwordTokenRepository.findByToken(resetRequest.getToken());
        if (token != null) {

            PasswordToken realToken = token.get();
            Optional<User> user = userRepository.findByEmail(realToken.getUser().getEmail());
            User realUser = user.get();
            return ResponseEntity.ok(new PasswordChangeResponse(realUser.getEmail()));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Reset Link"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {

        Optional<User> user = userRepository.findByEmail(passwordChangeRequest.getEmail());
        if (user != null) {
            User realuser = user.get();
            realuser.setPassword(encoder.encode(passwordChangeRequest.getPassword()));

            userRepository.saveAndFlush(realuser);

            return ResponseEntity.ok(new MessageResponse("Password was reset successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Reset Link"));
    }

    @CrossOrigin
    @GetMapping("/pending-counselors")
    public ResponseEntity<?> getPendingCounselors() {
        Role role = new Role(4L,currentRole.counselorPending);
        return new ResponseEntity<>(userRepository.findAllByRolesContaining(role), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/all-counselors")
    public ResponseEntity<?> getAllCounselors() {
        Role role = new Role(3L,currentRole.counselorRole);
        return new ResponseEntity<>(userRepository.findAllByRolesContaining(role), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/archived-counselors")
    public ResponseEntity<?> getArchivedCounselors() {
        Role role = new Role(5L,currentRole.counselorArchived);
        return new ResponseEntity<>(userRepository.findAllByRolesContaining(role), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/counselor-groups")
    public ResponseEntity<?> getCounselorGroups(@Valid @RequestBody GroupInfoRequest groupInfoRequest) {

        return new ResponseEntity<>(userRepository.findAllByGroup(groupInfoRequest.getGroup()), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/change-counselorGroup")
    public ResponseEntity<?> ChangeCounselorGroups(@Valid @RequestBody ChangeCounselorRequest counselorRequest) {

        Optional<User> counselor = userRepository.findById(counselorRequest.getId());

        if( counselor != null){
            User realCounselor = counselor.get();

            realCounselor.setGroup(counselorRequest.getGroup());
            userRepository.saveAndFlush(realCounselor);
            return ResponseEntity.ok(new MessageResponse("New Group has been set"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: User"));
    }

    @CrossOrigin
    @PostMapping("/change-counselor-active")
    public ResponseEntity<?> changeCounselorRole(@Valid @RequestBody ChangeCounselorRequest counselorRequest) {
        // System.out.println(counselorRequest.getId());
        // System.out.println(counselorRequest.getRole());
        Optional<User> counselor = userRepository.findById(counselorRequest.getId());

        if( counselor != null){
            User realCounselor = counselor.get();
            Role roled = new Role(3L,currentRole.counselorRole);
            List<Role> newRoles = new ArrayList<>();
            newRoles.add(roled);
            realCounselor.setRoles(newRoles);
            realCounselor.setGroup("none");
            userRepository.saveAndFlush(realCounselor);
            return ResponseEntity.ok(new MessageResponse("New Role has been set"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Role or User"));
    }

    @CrossOrigin
    @PostMapping("/change-counselor-archived")
    public ResponseEntity<?> changeCounselorArchive(@Valid @RequestBody ChangeCounselorRequest counselorRequest) {

        Optional<User> counselor = userRepository.findById(counselorRequest.getId());

        if( counselor != null){
            User realCounselor = counselor.get();
            Role roled = new Role(5L,currentRole.counselorArchived);
            List<Role> newRoles = new ArrayList<>();
            newRoles.add(roled);
            realCounselor.setRoles(newRoles);
            realCounselor.setGroup("inactive");
            userRepository.saveAndFlush(realCounselor);
            return ResponseEntity.ok(new MessageResponse("New Role has been set"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Role or User"));
    }

    @CrossOrigin
    @PostMapping("/delete-counselor")
    public ResponseEntity<?> deleteCounselor(@Valid @RequestBody ChangeCounselorRequest counselorRequest) {

        Optional<User> counselor = userRepository.findById(counselorRequest.getId());

        if( counselor != null){
            User realCounselor = counselor.get();
            userRepository.deleteById(counselorRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Counselor Deleted"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid User"));
    }



}
