package com.campizza.backend.campizzabackend.data;
import com.campizza.backend.campizzabackend.model.HouseHold;


import com.campizza.backend.campizzabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<HouseHold, Long> {
    Optional<HouseHold> findByUser(User user);

    Optional<HouseHold> findById(Long id);

    Boolean existsByUser(User user);
}
