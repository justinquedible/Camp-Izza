// DATA IS FOR DATA ACCESS. IT UTILIZES JPA REPOSITORY THAT CONTAINS BASIC CRUD OPERATIONS

package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Role;
import com.campizza.backend.campizzabackend.model.currentRole;
import com.campizza.backend.campizzabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(currentRole role);


}
