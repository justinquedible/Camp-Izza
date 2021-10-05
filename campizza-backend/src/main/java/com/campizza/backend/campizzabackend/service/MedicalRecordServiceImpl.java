package com.campizza.backend.campizzabackend.service;
import com.campizza.backend.campizzabackend.data.MedicalRecordRepository;
import com.campizza.backend.campizzabackend.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getMedicalRecordsAll(){
        return (List<MedicalRecord>) medicalRecordRepository.findAll();
    }

    public void createMedicalRecord(MedicalRecord medicalRecord){
        medicalRecordRepository.saveAndFlush(medicalRecord);
    }

}
