package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroupsAll();

    void createGroup(Group platoon);

    public Camper createCamper(Camper camper);
}
