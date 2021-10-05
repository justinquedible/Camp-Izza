package com.campizza.backend.campizzabackend.model;

import com.campizza.backend.campizzabackend.model.currentRole;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private currentRole roleName;



    public Role(){

    }

    public Role(Long id, currentRole roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(currentRole roleName){
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public currentRole getRoleName() {
        return roleName;
    }

    public void setRoleName(currentRole roleName) {
        this.roleName = roleName;
    }

    public String getStringRole(){return this.roleName.toString(); }
}



