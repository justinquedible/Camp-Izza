package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.CamperRepository;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campizza.backend.campizzabackend.data.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CamperRepository camperRepository;

    public List<Group> getGroupsAll() {
        return (List<Group>) groupRepository.findAll();
    }

    public void createGroup(Group platoon) {
//        for(Camper camper: group.getCampers())
//            camper.setGroup(group);
        groupRepository.saveAndFlush(platoon);
    }

    public Camper createCamper(Camper camper) {
        return camperRepository.save(camper);
    }


}

