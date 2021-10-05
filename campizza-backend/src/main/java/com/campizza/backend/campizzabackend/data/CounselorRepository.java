package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor, Long> {
}
