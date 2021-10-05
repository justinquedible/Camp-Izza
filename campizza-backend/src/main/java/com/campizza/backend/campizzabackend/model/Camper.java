package com.campizza.backend.campizzabackend.model;

import com.campizza.backend.campizzabackend.security.payloads.CamperRequest;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "campers")
public class Camper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private HouseHold parent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String genderEnum;

    @Column(name = "dob")
    private String dobDate;


    @Column(name = "school", length = 100)
    private String schoolName;

    @Column(name = "grade")
    private Integer gradeNum; // Changing to int instead varchar 1

    @Column(name = "shirt_size")
    private String shirtEnum;

    @Column(name = "comment")
    private String comment;

    @Column(name = "num_shirts")
    private int numShirts;

    @Column(name = "group_name")
    private String group;

    @Column(name = "credit")
    private Float credit;

    @Column(name = "paid")
    private Float paid;

    //@Column(name = "week_choices")
    //private List<String> = new ArrayList<String> weekChoices ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id", referencedColumnName ="id")
    private MedicalRecord medicalRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    public Camper() {
    }

    public Camper(CamperRequest camperRequest, User user) {
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();


    }

    public void updateInfo(CamperRequest camperRequest, User user, MedicalRecord medicalRecord){
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();
        this.medicalRecord = medicalRecord; 
    }

    public void updateInfo(CamperRequest camperRequest, User user, MedicalRecord medicalRecord, HouseHold parent){
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();
        this.medicalRecord = medicalRecord;
        this.parent = parent;
    }

    public Camper(CamperRequest camperRequest, User user, MedicalRecord medicalRecord){
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();
        this.medicalRecord = medicalRecord;
    }

    public Camper(CamperRequest camperRequest, User user, MedicalRecord medicalRecord, HouseHold parent){
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();
        this.medicalRecord = medicalRecord;
        this.parent = parent;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Float getPaid() {
        return paid;
    }

    public void setPaid(Float paid) {
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public HouseHold getParent() {
        return parent;
    }

    public void setParent(HouseHold parent) {
        this.parent = parent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(String genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getShirtEnum() {
        return shirtEnum;
    }

    public void setShirtEnum(String shirtEnum) {
        this.shirtEnum = shirtEnum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Attendance getAttendanceRecord() {
        return attendance;
    }

    public void setAttendanceRecord(Attendance attendanceRecord) {
        this.attendance = attendanceRecord;
    }

    public int getNumShirts() {
        return numShirts;
    }

    public void setNumShirts(int numShirts) {
        this.numShirts = numShirts;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // If string does not match the following values, it will not be accepted.
}

enum Gender {
    MALE, FEMALE, OTHER
}

enum ShirtSize {
    XSMALL, SMALL, MEDIUM, LARGE, XLARGE
}