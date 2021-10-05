package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Optional<MedicalRecord> findByFirstNameAndLastNameAndUser_Id(String firstName, String lastName, Long id);
    Optional<MedicalRecord> findByFirstNameAndUser_Id(String firstName, Long id);

    Optional<MedicalRecord> findByCamper(Camper camper);
}
