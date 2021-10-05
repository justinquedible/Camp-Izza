/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             admins is our table
             columns are id, first_name, last_name, email, phone number
             primary key is id
*/
package com.campizza.backend.campizzabackend.model;

import javax.persistence.*;

@Entity
@Table(name = "yearlysessionpricing")
public class YearlySessionPricing {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
                                                        // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider

    @Column(name = "pricing_id")
    private Long id;

    @Column(name = "oneweek_am_early")
    private float week_am_early;

    @Column(name = "oneweek_pm_early")
    private float week_pm_early;

    @Column(name = "oneweek_full_early")
    private float week_full_early;

    @Column(name = "oneweek_am_late")
    private float week_am_late;

    @Column(name = "oneweek_pm_late")
    private float week_pm_late;

    @Column(name = "oneweek_full_late")
    private float week_full_late;

    @Column(name = "holiday_am_early")
    private float holiday_am_early;

    @Column(name = "holiday_pm_early")
    private float holiday_pm_early;

    @Column(name = "holiday_full_early")
    private float holiday_full_early;

    @Column(name = "holiday_am_late")
    private float holiday_am_late;

    @Column(name = "holiday_pm_late")
    private float holiday_pm_late;

    @Column(name = "holiday_full_late")
    private float holiday_full_late;

    @Column(name = "oneday_am_early")
    private float day_am_early;

    @Column(name = "oneday_pm_early")
    private float day_pm_early;

    @Column(name = "oneday_full_early")
    private float day_full_early;

    @Column(name = "extended_care")
    private float extended_care;

    @Column(name = "extra_shirt")
    private float extra_shirt;


    // Default constructor
    public YearlySessionPricing() {
    }
    // Constructor
    public YearlySessionPricing(Long id, float week_am_early, float week_pm_early, float week_full_early, float week_am_late, float week_pm_late, float week_full_late, float holiday_am_early, float holiday_pm_early, float holiday_full_early, float holiday_am_late, float holiday_pm_late, float holiday_full_late, float day_am_early, float day_pm_early, float day_full_early, float extended_care, float extra_shirt) {
        this.id = id;
        this.week_am_early = week_am_early;
        this.week_pm_early = week_pm_early;
        this.week_full_early = week_full_early;
        this.week_am_late = week_am_late;
        this.week_pm_late = week_pm_late;
        this.week_full_late = week_full_late;
        this.holiday_am_early = holiday_am_early;
        this.holiday_pm_early = holiday_pm_early;
        this.holiday_full_early = holiday_full_early;
        this.holiday_am_late = holiday_am_late;
        this.holiday_pm_late = holiday_pm_late;
        this.holiday_full_late = holiday_full_late;
        this.day_am_early = day_am_early;
        this.day_pm_early = day_pm_early;
        this.day_full_early = day_full_early;
        this.extended_care = extended_care;
        this.extra_shirt = extra_shirt;
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

    public float getWeek_am_early() {
        return week_am_early;
    }

    public void setWeek_am_early(float week_am_early) {
        this.week_am_early = week_am_early;
    }

    public float getWeek_pm_early() {
        return week_pm_early;
    }

    public void setWeek_pm_early(float week_pm_early) {
        this.week_pm_early = week_pm_early;
    }

    public float getWeek_full_early() {
        return week_full_early;
    }

    public void setWeek_full_early(float week_full_early) {
        this.week_full_early = week_full_early;
    }

    public float getWeek_am_late() {
        return week_am_late;
    }

    public void setWeek_am_late(float week_am_late) {
        this.week_am_late = week_am_late;
    }

    public float getWeek_pm_late() {
        return week_pm_late;
    }

    public void setWeek_pm_late(float week_pm_late) {
        this.week_pm_late = week_pm_late;
    }

    public float getWeek_full_late() {
        return week_full_late;
    }

    public void setWeek_full_late(float week_full_late) {
        this.week_full_late = week_full_late;
    }

    public float getHoliday_am_early() {
        return holiday_am_early;
    }

    public void setHoliday_am_early(float holiday_am_early) {
        this.holiday_am_early = holiday_am_early;
    }

    public float getHoliday_pm_early() {
        return holiday_pm_early;
    }

    public void setHoliday_pm_early(float holiday_pm_early) {
        this.holiday_pm_early = holiday_pm_early;
    }

    public float getHoliday_full_early() {
        return holiday_full_early;
    }

    public void setHoliday_full_early(float holiday_full_early) {
        this.holiday_full_early = holiday_full_early;
    }

    public float getHoliday_am_late() {
        return holiday_am_late;
    }

    public void setHoliday_am_late(float holiday_am_late) {
        this.holiday_am_late = holiday_am_late;
    }

    public float getHoliday_pm_late() {
        return holiday_pm_late;
    }

    public void setHoliday_pm_late(float holiday_pm_late) {
        this.holiday_pm_late = holiday_pm_late;
    }

    public float getHoliday_full_late() {
        return holiday_full_late;
    }

    public void setHoliday_full_late(float holiday_full_late) {
        this.holiday_full_late = holiday_full_late;
    }

    public float getDay_am_early() {
        return day_am_early;
    }

    public void setDay_am_early(float day_am_early) {
        this.day_am_early = day_am_early;
    }

    public float getDay_pm_early() {
        return day_pm_early;
    }

    public void setDay_pm_early(float day_pm_early) {
        this.day_pm_early = day_pm_early;
    }

    public float getDay_full_early() {
        return day_full_early;
    }

    public void setDay_full_early(float day_full_early) {
        this.day_full_early = day_full_early;
    }

    public float getExtended_care() {
        return extended_care;
    }

    public void setExtended_care(float extended_care) {
        this.extended_care = extended_care;
    }

    public float getExtra_shirt() {
        return extra_shirt;
    }

    public void setExtra_shirt(float extra_shirt) {
        this.extra_shirt = extra_shirt;
    }
}
