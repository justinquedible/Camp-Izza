package com.campizza.backend.campizzabackend.model;

import com.campizza.backend.campizzabackend.security.payloads.ParentRequest;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "parents")
public class HouseHold {

    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
    // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider

    @Column(name = "id")
    private Long id;

    @Column(name = "guardian_name_first1")
    private String guardianNameFirst1;

    @Column(name = "guardaian_name_last1")
    private String guardianNameLast1;

    @Column(name = "guardian_name_first2")
    private String guardianNameFirst2;

    @Column(name = "guardian_name_last2")
    private String guardianNameLast2;

    @Column(name = "guardian_email1")
    private String guardianEmail1;

    @Column(name = "guardian_email2")
    private String guardianEmail2;

    @Column(name = "guardian1_phone1")
    private String guardian1Phone1;

    @Column(name = "guaridan1_phone2")
    private String guardian1Phone2;

    @Column(name = "guardian2_phone1")
    private String guardian2Phone1;

    @Column(name = "guardian2_phone2")
    private String guardian2Phone2;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_postalcode")
    private String zipPostalCode;
    //@Column(name = "payment_Id")
   // private unknown_type_yet paymentID;

    @Column(name = "emergency1first")
    private String emergency1first;

    @Column(name = "emergency1last")
    private String emergency1last;

    @Column(name = "emergency1relationship")
    private String emergency1relationship;

    @Column(name = "emergency1areacode")
    private String emergency1areacode;

    @Column(name = "emergency1Phone")
    private String emergency1Phone;

    @Column(name = "emergency1authorized")
    private String emergency1authorized;

    @Column(name = "emergency2first")
    private String emergency2first;

    @Column(name = "emergency2last")
    private String emergency2last;

    @Column(name = "emergency2relationship")
    private String emergency2relationship;

    @Column(name = "emergency2areacode")
    private String emergency2areacode;

    @Column(name = "emergency2Phone")
    private String emergency2Phone;

    @Column(name = "emergency2authorized")
    private String emergency2authorized;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    //DEFAULT CONSTRUCTOR
    public HouseHold(){
    }

    public HouseHold(ParentRequest parentRequest, User user){
        this.guardianNameFirst1 = parentRequest.getGuardianNameFirst1();
        this.guardianNameLast1 = parentRequest.getGuardianNameLast1();
        this.guardianNameFirst2 = parentRequest.getGuardianNameFirst2();
        this.guardianNameLast2 = parentRequest.getGuardianNameLast2();
        this.guardianEmail1 = parentRequest.getGuardianEmail1();
        this.guardianEmail2 = parentRequest.getGuardianEmail2();
        this.guardian1Phone1 = parentRequest.getGuardian1Phone1();
        this.guardian1Phone2 = parentRequest.getGuardian1Phone2();
        this.guardian2Phone1 = parentRequest.getGuardian2Phone1();
        this.guardian2Phone2 = parentRequest.getGuardian2Phone2();
        this.address1 = parentRequest.getAddress1();
        this.address2 = parentRequest.getAddress2();
        this.country = parentRequest.getCountry();
        this.city = parentRequest.getCity();
        this.state = parentRequest.getState();
        this.zipPostalCode = parentRequest.getZipPostalCode();
        this.emergency1first = parentRequest.getEmergency1first();
        this.emergency1last = parentRequest.getEmergency1last();
        this.emergency1relationship = parentRequest.getEmergency1relationship();
        this.emergency1areacode = parentRequest.getEmergency1areacode();
        this.emergency1Phone = parentRequest.getEmergency1Phone();
        this.emergency1authorized = parentRequest.getEmergency1authorized();
        this.emergency2first = parentRequest.getEmergency2first();
        this.emergency2last = parentRequest.getEmergency2last();
        this.emergency2relationship = parentRequest.getEmergency2relationship();
        this.emergency2areacode = parentRequest.getEmergency2areacode();
        this.emergency2Phone = parentRequest.getEmergency2Phone();
        this.emergency2authorized = parentRequest.getEmergency2authorized();
        this.user = user;
    }

    // CONSTRUCTOR WITH ALL PROPONENTS
    public HouseHold(Long id, String guardianNameFirst1, String guardianNameLast1, String guardianNameFirst2,
                     String guardianNameLast2, String guardianEmail1, String guardianEmail2, String guardian1Phone1,
                     String guardian1Phone2, String guardian2Phone1, String guardian2Phone2, String address1,
                     String address2, String country, String city, String state, String zipPostalCode, User user) {
        this.guardianNameFirst1 = guardianNameFirst1;
        this.guardianNameLast1 = guardianNameLast1;
        this.guardianNameFirst2 = guardianNameFirst2;
        this.guardianNameLast2 = guardianNameLast2;
        this.guardianEmail1 = guardianEmail1;
        this.guardianEmail2 = guardianEmail2;
        this.guardian1Phone1 = guardian1Phone1;
        this.guardian1Phone2 = guardian1Phone2;
        this.guardian2Phone1 = guardian2Phone1;
        this.guardian2Phone2 = guardian2Phone2;
        this.address1 = address1;
        this.address2 = address2;
        this.country = country;
        this.city = city;
        this.state = state;
        this.zipPostalCode = zipPostalCode;
        this.user = user;
    }

    // CONSTRUCTOR WITH MANDATORY PROPONENTS
    public HouseHold(Long id, String guardianNameFirst1, String guardianNameLast1, String guardianEmail1, String guardian1Phone1, String guardian2Phone1, String address1, String country, String city, String zipPostalCode) {
        this.guardianNameFirst1 = guardianNameFirst1;
        this.guardianNameLast1 = guardianNameLast1;
        this.guardianEmail1 = guardianEmail1;
        this.guardian1Phone1 = guardian1Phone1;
        this.guardian2Phone1 = guardian2Phone1;
        this.address1 = address1;
        this.country = country;
        this.city = city;
        this.zipPostalCode = zipPostalCode;
    }

    public void updateInfo(ParentRequest parentRequest){
        this.guardianNameFirst1 = parentRequest.getGuardianNameFirst1();
        this.guardianNameLast1 = parentRequest.getGuardianNameLast1();
        this.guardianNameFirst2 = parentRequest.getGuardianNameFirst2();
        this.guardianNameLast2 = parentRequest.getGuardianNameLast2();
        // System.out.println("Parent.UpdateInfo()");
        // System.out.println(parentRequest.getEmergency1Phone());
        this.guardianEmail1 = parentRequest.getGuardianEmail1();
        this.guardianEmail2 = parentRequest.getGuardianEmail2();
        this.guardian1Phone1 = parentRequest.getGuardian1Phone1();
        this.guardian1Phone2 = parentRequest.getGuardian1Phone2();
        this.guardian2Phone1 = parentRequest.getGuardian2Phone1();
        this.guardian2Phone2 = parentRequest.getGuardian2Phone2();
        this.address1 = parentRequest.getAddress1();
        this.address2 = parentRequest.getAddress2();
        this.country = parentRequest.getCountry();
        this.city = parentRequest.getCity();
        this.state = parentRequest.getState();
        this.zipPostalCode = parentRequest.getZipPostalCode();

        this.emergency1first = parentRequest.getEmergency1first();
        this.emergency1last = parentRequest.getEmergency1last();
        this.emergency1relationship = parentRequest.getEmergency1relationship();
        this.emergency1areacode = parentRequest.getEmergency1areacode();
        this.emergency1Phone = parentRequest.getEmergency1Phone();
        this.emergency1authorized = parentRequest.getEmergency1authorized();
        this.emergency2first = parentRequest.getEmergency2first();
        this.emergency2last = parentRequest.getEmergency2last();
        this.emergency2relationship = parentRequest.getEmergency2relationship();
        this.emergency2areacode = parentRequest.getEmergency2areacode();
        this.emergency2Phone = parentRequest.getEmergency2Phone();
        this.emergency2authorized = parentRequest.getEmergency2authorized();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public String getGuardianNameFirst1() {
        return guardianNameFirst1;
    }

    public void setGuardianNameFirst1(String guardianNameFirst1) {
        this.guardianNameFirst1 = guardianNameFirst1;
    }

    public String getGuardianNameLast1() {
        return guardianNameLast1;
    }

    public void setGuardianNameLast1(String guardianNameLast1) {
        this.guardianNameLast1 = guardianNameLast1;
    }

    public String getGuardianNameFirst2() {
        return guardianNameFirst2;
    }

    public void setGuardianNameFirst2(String guardianNameFirst2) {
        this.guardianNameFirst2 = guardianNameFirst2;
    }

    public String getGuardianNameLast2() {
        return guardianNameLast2;
    }

    public void setGuardianNameLast2(String guardianNameLast2) {
        this.guardianNameLast2 = guardianNameLast2;
    }

    public String getGuardianEmail1() {
        return guardianEmail1;
    }

    public void setGuardianEmail1(String guardianEmail1) {
        this.guardianEmail1 = guardianEmail1;
    }

    public String getGuardianEmail2() {
        return guardianEmail2;
    }

    public void setGuardianEmail2(String guardianEmail2) {
        this.guardianEmail2 = guardianEmail2;
    }

    public String getGuardian1Phone1() {
        return guardian1Phone1;
    }

    public void setGuardian1Phone1(String guardian1Phone1) {
        this.guardian1Phone1 = guardian1Phone1;
    }

    public String getGuardian1Phone2() {
        return guardian1Phone2;
    }

    public void setGuardian1Phone2(String guardian1Phone2) {
        this.guardian1Phone2 = guardian1Phone2;
    }

    public String getGuardian2Phone1() {
        return guardian2Phone1;
    }

    public void setGuardian2Phone1(String guardian2Phone1) {
        this.guardian2Phone1 = guardian2Phone1;
    }

    public String getGuardian2Phone2() {
        return guardian2Phone2;
    }

    public void setGuardian2Phone2(String guardian2Phone2) {
        this.guardian2Phone2 = guardian2Phone2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getEmergency1first() {
        return emergency1first;
    }

    public void setEmergency1first(String emergency1first) {
        this.emergency1first = emergency1first;
    }

    public String getEmergency1last() {
        return emergency1last;
    }

    public void setEmergency1last(String emergency1last) {
        this.emergency1last = emergency1last;
    }

    public String getEmergency1relationship() {
        return emergency1relationship;
    }

    public void setEmergency1relationship(String emergency1relationship) {
        this.emergency1relationship = emergency1relationship;
    }

    public String getEmergency1areacode() {
        return emergency1areacode;
    }

    public void setEmergency1areacode(String emergency1areacode) {
        this.emergency1areacode = emergency1areacode;
    }

    public String getEmergency1Phone() {
        return emergency1Phone;
    }

    public void setEmergency1Phone(String emergency1Phone) {
        this.emergency1Phone = emergency1Phone;
    }

    public String getEmergency1authorized() {
        return emergency1authorized;
    }

    public void setEmergency1authorized(String emergency1authorized) {
        this.emergency1authorized = emergency1authorized;
    }

    public String getEmergency2first() {
        return emergency2first;
    }

    public void setEmergency2first(String emergency2first) {
        this.emergency2first = emergency2first;
    }

    public String getEmergency2last() {
        return emergency2last;
    }

    public void setEmergency2last(String emergency2last) {
        this.emergency2last = emergency2last;
    }

    public String getEmergency2relationship() {
        return emergency2relationship;
    }

    public void setEmergency2relationship(String emergency2relationship) {
        this.emergency2relationship = emergency2relationship;
    }

    public String getEmergency2areacode() {
        return emergency2areacode;
    }

    public void setEmergency2areacode(String emergency2areacode) {
        this.emergency2areacode = emergency2areacode;
    }

    public String getEmergency2Phone() {
        return emergency2Phone;
    }

    public void setEmergency2Phone(String emergency2Phone) {
        this.emergency2Phone = emergency2Phone;
    }

    public String getEmergency2authorized() {
        return emergency2authorized;
    }

    public void setEmergency2authorized(String emergency2authorized) {
        this.emergency2authorized = emergency2authorized;
    }
}
