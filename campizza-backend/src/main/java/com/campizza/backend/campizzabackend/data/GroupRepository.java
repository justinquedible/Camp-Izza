package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
