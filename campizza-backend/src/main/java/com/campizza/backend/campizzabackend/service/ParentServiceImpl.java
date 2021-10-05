package com.campizza.backend.campizzabackend.service;
import com.campizza.backend.campizzabackend.data.ParentRepository;
import com.campizza.backend.campizzabackend.model.HouseHold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentRepository parentRepository;

    public List<HouseHold> getParentsAll(){
        return (List<HouseHold>) parentRepository.findAll();
    }

    public void createParent(HouseHold parent){
        parentRepository.saveAndFlush(parent);
    }
}
