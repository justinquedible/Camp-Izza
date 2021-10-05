package com.campizza.backend.campizzabackend.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="attendance_campers",
            joinColumns = @JoinColumn(name = "attendance_id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id"))
    private Set<Camper> campers;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "counselor_id")
//    private Counselor counselor;

    @Column(name="bonus_campers")
    @ElementCollection(targetClass=String.class)
    private Set<String> bonusCampers;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date currDate;

    @Column(name="am", nullable = false)
    private boolean timeAM = false;

    @Column(name="pm", nullable = false)
    private boolean timePM = false;

    public Attendance(Date date, String time) {
        this.currDate = date;
        if(time.equals("am")){
            this.timeAM = true;
        }
        if(time.equals("pm")){
            this.timePM = true;
        }
        if(time.equals("full")){
            this.timePM = true;
            this.timeAM = true;
        }

    }

    public Attendance() {

    }

    public Long getId() {
        return id;
    }

    public Set<Camper> getCampers() {
        return campers;
    }

    public void setCampers(Set<Camper> campers) {
        this.campers = campers;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public boolean isTimeAM() {
        return timeAM;
    }

    public void setTimeAM(boolean timeAM) {
        this.timeAM = timeAM;
    }

    public boolean isTimePM() {
        return timePM;
    }

    public void setTimePM(boolean timePM) {
        this.timePM = timePM;
    }

    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }

    public void removeCamper(Camper camper) {
        this.campers.remove(camper);
    }

    public boolean containsCamper(Camper camper) {
        return this.campers.contains(camper);
    }

    public void removeBonusCamper(String camperName) {
        this.bonusCampers.remove(camperName);
    }

    public boolean containsBonusCamper(String camperName) {
        return this.bonusCampers.contains(camperName);
    }

    public Set<String> getBonusCampers() {
        return bonusCampers;
    }

    public void setBonusCampers(Set<String> bonusCampers) {
        this.bonusCampers = bonusCampers;
    }

    public void addBonusCampers(String camperName) {
        this.bonusCampers.add(camperName);
    }


    //    public Counselor getCounselor() {
//        return counselor;
//    }
}
