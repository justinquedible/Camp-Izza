// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Holiday;
import com.campizza.backend.campizzabackend.model.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
   //List<Weeks> findAllByCampers(Camper camper);
//    List<Weeks> findAllByCampers(Camper camper);
    //List<Weeks> findAllByCampersAMAndCampersPM(Set<Camper> campersAM, Set<Camper> campersPM);


}
