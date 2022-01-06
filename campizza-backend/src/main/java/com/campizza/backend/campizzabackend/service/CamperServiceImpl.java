package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campizza.backend.campizzabackend.data.CamperRepository;

import java.util.List;

@Service
public class CamperServiceImpl implements CamperService {

    @Autowired
    private CamperRepository camperRepository;

    public List<Camper> getCampersAll() {
        return (List<Camper>) camperRepository.findAll();
    }

    public void createCamper(Camper camper) {
        camperRepository.saveAndFlush(camper);
    }

    public List<Camper> getParentCampers(Long id) {
        return (List<Camper>) camperRepository.findByUser_Id(id);
    }

    public List<Camper> getGroupCampers(String group) {
        return (List<Camper>) camperRepository.findByGroup(group);
    }

    public void removeCamper(Long id) {
        camperRepository.deleteCamperMedicalRecordById(id);
        camperRepository.deleteCamperById(id);
    }
}
