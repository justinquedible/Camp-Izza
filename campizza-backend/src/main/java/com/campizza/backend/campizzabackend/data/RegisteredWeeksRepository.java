// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.RegisteredWeeks;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisteredWeeksRepository extends JpaRepository<RegisteredWeeks, Long> {

//    Boolean existsByCamper(Camper camper);

//    Optional<RegisteredWeeks> findByCamper(Camper camper);

    Optional<RegisteredWeeks> findByCurrentYear(int year);

}
