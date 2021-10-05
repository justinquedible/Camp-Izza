package com.campizza.backend.campizzabackend.service;

import com.campizza.backend.campizzabackend.data.CounselorRepository;

import com.campizza.backend.campizzabackend.model.Counselor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselorServiceImpl implements CounselorService{

    @Autowired
    private CounselorRepository counselorRepository;

    public List<Counselor> getCounselorsAll() {
        return (List<Counselor>) counselorRepository.findAll();
    }

    public void createCounselor(Counselor counselor) {
        counselorRepository.saveAndFlush(counselor);
    }
}
