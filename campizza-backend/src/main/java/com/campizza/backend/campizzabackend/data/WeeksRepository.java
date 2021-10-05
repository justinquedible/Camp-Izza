// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WeeksRepository extends JpaRepository<Weeks, Long> {
   //  List<Weeks> findAllByCampers(Camper camper);
   // List<Weeks> findAllByCampersAMAndCampersPM(Set<Camper> campersAM, Set<Camper> campersPM);
    Optional<Weeks> findByAttendances_CurrDate(Date date);
}
