package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;

import java.util.List;

public interface CamperService {
    List<Camper> getCampersAll();

    void createCamper(Camper camper);

    List<Camper> getParentCampers(Long id);

    List<Camper> getGroupCampers(String group);

    void removeCamper(Long id);

}
