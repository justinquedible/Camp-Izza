/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             admins is our table
             columns are id, first_name, last_name, email, phone number
             primary key is id
*/
package com.campizza.backend.campizzabackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
                                                        // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider

    @Column(name = "admin_id")
    private Long id;

   @Column(name = "first_name")
    private String firstName;

   @Column(name = "last_name")
    private String lastName;

   @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;


    // Default constructor
    public Admin() {
    }
    // Constructor
    public Admin( Long id, String firstName, String lastName, String email, Long phoneNumber) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    // String output of Admin contents (for testing purposes)
    @Override
    public String toString() {
        return "Admin: id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone number=" + phoneNumber ;
    }

    // getters and setters for the attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
