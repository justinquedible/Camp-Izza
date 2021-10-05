/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             admins is our table
             columns are id, first_name, last_name, email, phone number
             primary key is id
*/
package com.campizza.backend.campizzabackend.model;

import javax.persistence.*;

@Entity
@Table(name = "emergencycontacts")
public class EmergencyContacts {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
                                                        // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider

    @Column(name = "emergency_contact_id")
    private Long id;

    @Column(name = "emergency_name_first1")
    private String firstName1;

    @Column(name = "emergency_name_last1")
    private String lastName1;

    @Column(name = "emergency_relationship1")
    private String relationship1;

    @Column(name = "emergency_phone1")
    private Long phoneNumber1;

    @Column(name = "emergency_authorized1")
    private Boolean authorized1;

    @Column(name = "emergency_name_first2")
    private String firstName2;

    @Column(name = "emergency_name_last2")
    private String lastName2;



    @Column(name = "emergency_relationship2")
    private String relationship2;

    @Column(name = "emergency_phone2")
    private Long phoneNumber2;

    @Column(name = "emergency_authorized2")
    private Boolean authorized2;


    // Default constructor
    public EmergencyContacts() {
    }
    // Constructor
    public EmergencyContacts(Long id, String firstName1, String lastName1, String relationship1, Long phoneNumber1, Boolean authorized1, String firstName2, String lastName2, String relationship2, Long phoneNumber2, Boolean authorized2) {
        this.id = id;
        this.firstName1 = firstName1;
        this.lastName1 = lastName1;
        this.relationship1 = relationship1;
        this.phoneNumber1 = phoneNumber1;
        this.authorized1 = authorized1;
        this.firstName2 = firstName2;
        this.lastName2 = lastName2;
        this.relationship2 = relationship2;
        this.phoneNumber2 = phoneNumber2;
        this.authorized2 = authorized2;
    }

//    // String output of Admin contents (for testing purposes)
//    @Override
//    public String toString() {
//        return "Admin: id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone number=" + phoneNumber ;
//    }

    // getters and setters for the attributes


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName1() {
        return firstName1;
    }

    public void setFirstName1(String firstName1) {
        this.firstName1 = firstName1;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(String relationship1) {
        this.relationship1 = relationship1;
    }

    public Long getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(Long phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Boolean getAuthorized1() {
        return authorized1;
    }

    public void setAuthorized1(Boolean authorized1) {
        this.authorized1 = authorized1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2;
    }

    public Long getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(Long phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Boolean getAuthorized2() {
        return authorized2;
    }

    public void setAuthorized2(Boolean authorized2) {
        this.authorized2 = authorized2;
    }
}
