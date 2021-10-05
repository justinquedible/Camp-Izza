package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.EmergencyContacts;

import java.util.List;

public interface EmergencyContactsService {
    List<EmergencyContacts>  getEmergencyContactsAll();

    void createEmergencyContacts(EmergencyContacts emergencycontacts);
}
