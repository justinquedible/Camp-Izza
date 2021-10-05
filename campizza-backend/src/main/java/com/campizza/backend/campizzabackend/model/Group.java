package com.campizza.backend.campizzabackend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "platoons")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private GroupName groupNameEnum;

    @OneToOne
    @JoinColumn(name = "counselor_id", referencedColumnName = "id")
    private Counselor counselor;

//
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "platoons",
//            cascade = CascadeType.ALL
//    )
//    private List<Camper> campers = new ArrayList<>();

    @Column(name = "session_limit")
    private Integer sessionLimit;

    public Group() {
    }


    public Long getId() {
        return id;
    }

    public GroupName getGroupNameEnum() {
        return groupNameEnum;
    }

    public void setGroupNameEnum(GroupName groupNameEnum) {
        this.groupNameEnum = groupNameEnum;
    }

    public Counselor getCounselor() {
        return counselor;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

//    public List<Camper> getCamper() {
//        return campers;
//    }

//    public void setCampers(List<Camper> campers) {
//        this.campers = campers;
//        campers.forEach(c -> c.setGroup(this));
//    }
//
//    public void addCamper(Camper c) {
//        campers.add(c);
//        if (c.getGroup() != this) {
//            c.setGroup(this);
//        }
//
//        this.setCampers(campers);
//    }

    public Integer getSessionLimit() {
        return sessionLimit;
    }

    public void setSessionLimit(Integer sessionLimit) {
        this.sessionLimit = sessionLimit;
    }
}

enum GroupName {
    DATE, COCONUT, PALMTREE, YOUNGADULT
}