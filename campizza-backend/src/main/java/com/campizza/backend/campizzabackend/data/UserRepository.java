// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Role;
import com.campizza.backend.campizzabackend.model.User;
import com.campizza.backend.campizzabackend.model.currentRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    Boolean existsByEmail(String email);

    List<User> findAllByRolesContaining(Role search);

    Void deleteById(long id);

    List<User> findAllByGroup(String group);


}
