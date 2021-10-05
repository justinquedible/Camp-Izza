// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.YearlySessionWeeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlySessionWeeksRepository extends JpaRepository<YearlySessionWeeks, Long> {
}
