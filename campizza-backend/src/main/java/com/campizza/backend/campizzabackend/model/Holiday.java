package com.campizza.backend.campizzabackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "holidays")
public class Holiday {

    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
    // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider
    @Column(name = "holiday_id")
    private Long holiday_id;

    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date holiday_date;

    public Holiday() {
    }

    public Holiday(Date holidayDate ){
        // System.out.println("In constructor");
        // System.out.println(holidayDate);
        this.holiday_date = holidayDate;
        // System.out.println(this.holiday_date);
    }

    public Date getHoliday() {
        return holiday_date;
    }

    public void setHoliday(Date holiday) {
        this.holiday_date = holiday;
    }

    public Long getHoliday_id() {
        return holiday_id;
    }

    public void setHoliday_id(Long holiday_id) {
        this.holiday_id = holiday_id;
    }
}
