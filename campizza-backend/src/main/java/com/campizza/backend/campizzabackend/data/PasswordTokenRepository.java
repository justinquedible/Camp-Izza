// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

    Optional<PasswordToken> findByToken(String passwordToken);



}
