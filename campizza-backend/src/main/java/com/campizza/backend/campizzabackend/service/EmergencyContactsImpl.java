package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.EmergencyContactRepository;
import com.campizza.backend.campizzabackend.model.EmergencyContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactsImpl implements EmergencyContactsService{

    @Autowired
    private EmergencyContactRepository emergencyContactsRepository;

    public List<EmergencyContacts> getEmergencyContactsAll() {
        return (List<EmergencyContacts>) emergencyContactsRepository.findAll();
    }

    public void createEmergencyContacts(EmergencyContacts emergencyContacts) {
        emergencyContactsRepository.saveAndFlush(emergencyContacts);
    }
}
