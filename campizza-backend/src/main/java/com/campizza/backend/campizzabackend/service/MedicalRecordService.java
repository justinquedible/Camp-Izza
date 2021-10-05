package com.campizza.backend.campizzabackend.service;
import com.campizza.backend.campizzabackend.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> getMedicalRecordsAll();

    void createMedicalRecord(MedicalRecord medicalRecord);
}
