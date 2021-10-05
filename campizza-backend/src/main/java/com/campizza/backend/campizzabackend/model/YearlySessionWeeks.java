/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             yearlysessionweeks is our table
             columns are
             primary key is id

*/
package com.campizza.backend.campizzabackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "yearlysessionweeks")
public class YearlySessionWeeks {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way  increment the field
                                                        // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider
                                                        // @TODO: This database has two variations in the original table

    @Column(name = "yearly_session_id")
    private Long id;

    @OneToMany
    @Column(name = "weeks")
    private List<Weeks> wks;

    @Column(name = "active_weeks")
    private int active_wks;

    @Column(name = "current_year")
    private int current_yrs;

    @Column(name = "holiday_week")
    private int holiday_wks;



    // Default constructor
    public YearlySessionWeeks() {
    }
    // Constructor
    public YearlySessionWeeks(Long id, List<Weeks> wks, int active_wks, int current_yrs, int holiday_wks) {
        this.id = id;
        this.wks = wks;
        this.active_wks = active_wks;
        this.current_yrs = current_yrs;
        this.holiday_wks = holiday_wks;
    }

    // String output of Admin contents (for testing purposes)
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

    public List<Weeks> getWks() {
        return wks;
    }

    public void setWks(List<Weeks> wks) {
        this.wks = wks;
    }
    public int getActive_wks() {
        return active_wks;
    }

    public void setActive_wks(int active_wks) {
        this.active_wks = active_wks;
    }

    public int getCurrent_yrs() {
        return current_yrs;
    }

    public void setCurrent_yrs(int current_yrs) {
        this.current_yrs = current_yrs;
    }

    public int getHoliday_wks() {
        return holiday_wks;
    }

    public void setHoliday_wks(int holiday_wks) {
        this.holiday_wks = holiday_wks;
    }
}
