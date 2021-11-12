/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             admins is our table
             columns are id, first_name, last_name, email, phone number
             primary key is id
*/
package com.campizza.backend.campizzabackend.model;

import com.campizza.backend.campizzabackend.security.payloads.ScheduleRequest;
import com.campizza.backend.campizzabackend.security.payloads.SessionEditWeekRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "registered_weeks")
public class RegisteredWeeks {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field

    @Column(name = "id")
    private Long id;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn
//    private Camper camper;
    @JsonIgnoreProperties("registeredWeeks")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "week_id")
    private List<Weeks> allRegWeeks = new ArrayList<>();

    @Column(name = "current_year")
    private int currentYear;

    @Column(name = "pricing_base_rate") // Early Bird Rate
    private int pricingBaseRate;

    @Column(name = "pricing_extra_rate") // Regular Rate
    private int pricingExtraRate;

    @Column(name = "extended_rate") // Extended Care
    private int extendedCareRate;

    @Column(name = "extra_shirts")
    private int shirtPrice;

    @Column(name = "dates_am_limit", columnDefinition = "int default 30")
    private int dateAMLimit;

    @Column(name = "dates_pm_limit", columnDefinition = "int default 30")
    private int datePMLimit;

    @Column(name = "coco_am_limit", columnDefinition = "int default 30")
    private int cocoAMLimit;

    @Column(name = "coco_pm_limit", columnDefinition = "int default 30")
    private int cocoPMLimit;

    @Column(name = "tree_am_limit", columnDefinition = "int default 30")
    private int treeAMLimit;

    @Column(name = "tree_pm_limit", columnDefinition = "int default 30")
    private int treePMLimit;

    @Column(name = "lead_am_limit", columnDefinition = "int default 30")
    private int leadAMLimit;

    @Column(name = "lead_pm_limit", columnDefinition = "int default 30")
    private int leadPMLimit;

    @Column(name = "early_cutoff")
    @Temporal(TemporalType.DATE)
    private Date early_cutoff;

    @Column(name = "amTimeStart")
    private String amTimeStart;

    @Column(name = "amTimeEnd")
    private String amTimeEnd;

    @Column(name = "amECTimeStart")
    private String amECTimeStart;

    @Column(name = "amECTimeEnd")
    private String amECTimeEnd;

    @Column(name = "pmTimeStart")
    private String pmTimeStart;

    @Column(name = "pmTimeEnd")
    private String pmTimeEnd;

    @Column(name = "pmECTimeStart")
    private String pmECTimeStart;

    @Column(name = "pmECTimeEnd")
    private String pmECTimeEnd;

    @Column(name = "fullTimeStart")
    private String fullTimeStart;

    @Column(name = "fullTimeEnd")
    private String fullTimeEnd;

    @Column(name = "fullECTimeStart")
    private String fullECTimeStart;

    @Column(name = "fullECTimeEnd")
    private String fullECTimeEnd;

    @Column(name = "amSelected ")
    private String amSelected;

    @Column(name = "amECSelected")
    private String amECSelected;

    @Column(name = "pmSelected")
    private String pmSelected;

    @Column(name = "pmECSelected")
    private String pmECSelected;

    @Column(name = "fullSelected")
    private String fullSelected;

    @Column(name = "fullECSelected")
    private String fullECSelected;

    public RegisteredWeeks(){

    };


    public void updateSessionTimes(SessionEditWeekRequest request){

        this.amTimeStart =   request.getAmTimeStart();
        this.amTimeEnd = request.getAmTimeEnd();       ;
        this.amECTimeStart = request.getAmECTimeStart();    ;
        this.amECTimeEnd =      request.getAmECTimeEnd()    ;
        this.pmTimeStart =   request.getPmTimeStart()       ;
        this.pmTimeEnd =       request.getPmTimeEnd()   ;
        this.pmECTimeStart =     request.getPmECTimeStart()     ;
        this.pmECTimeEnd =   request.getPmECTimeEnd()       ;
        this.fullTimeStart =  request.getFullTimeStart()        ;
        this.fullTimeEnd =     request.getFullTimeEnd()    ;
        this.fullECTimeStart =       request.getFullECTimeStart()   ;
        this.fullECTimeEnd =   request.getFullECTimeEnd()       ;

        this.amSelected = request.getAmSelected();     ;
        this.amECSelected =    request.getAmECSelected()      ;
        this.pmSelected = request.getPmSelected();      ;
        this.pmECSelected =    request.getPmECSelected()      ;
        this.fullSelected =    request.getFullSelected()      ;
        this.fullECSelected =   request.getFullECSelected()       ;
    }

    public void updateInfo(ScheduleRequest scheduleRequest){
        this.currentYear = scheduleRequest.getCurrentYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Weeks> getWeeks() {
        return allRegWeeks;
    }

    public void setWeeks(List<Weeks> weeks) {
        this.allRegWeeks = weeks;
    }

    public void addWeek(Weeks week) {
        this.allRegWeeks.add(week);
        // System.out.println(week.getWeekId());
        // System.out.println(this.id);
        for (Weeks i:
             this.allRegWeeks) {
            // System.out.println(i.getWeekId());
        }
        // System.out.println(this.allRegWeeks);
    }

    public int getExtendedCareRate() {
        return extendedCareRate;
    }

    public void setExtendedCareRate(int extendedCareRate) {
        this.extendedCareRate = extendedCareRate;
    }

    public int getShirtPrice() {
        return shirtPrice;
    }

    public void setShirtPrice(int shirtPrice) {
        this.shirtPrice = shirtPrice;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getPricingBaseRate() {
        return pricingBaseRate;
    }

    public void setPricingBaseRate(int pricingBaseRate) {
        this.pricingBaseRate = pricingBaseRate;
    }

    public int getPricingExtraRate() {
        return pricingExtraRate;
    }

    public void setPricingExtraRate(int pricingExtraRate) {
        this.pricingExtraRate = pricingExtraRate;
    }

    public int getDateAMLimit() {
        return dateAMLimit;
    }

    public void setDateAMLimit(int dateAMLimit) {
        this.dateAMLimit = dateAMLimit;
    }

    public int getDatePMLimit() {
        return datePMLimit;
    }

    public void setDatePMLimit(int datePMLimit) {
        this.datePMLimit = datePMLimit;
    }

    public int getCocoAMLimit() {
        return cocoAMLimit;
    }

    public void setCocoAMLimit(int cocoAMLimit) {
        this.cocoAMLimit = cocoAMLimit;
    }

    public int getCocoPMLimit() {
        return cocoPMLimit;
    }

    public void setCocoPMLimit(int cocoPMLimit) {
        this.cocoPMLimit = cocoPMLimit;
    }

    public int getTreeAMLimit() {
        return treeAMLimit;
    }

    public void setTreeAMLimit(int treeAMLimit) {
        this.treeAMLimit = treeAMLimit;
    }

    public int getTreePMLimit() {
        return treePMLimit;
    }

    public void setTreePMLimit(int treePMLimit) {
        this.treePMLimit = treePMLimit;
    }

    public int getLeadAMLimit() {
        return leadAMLimit;
    }

    public void setLeadAMLimit(int leadAMLimit) {
        this.leadAMLimit = leadAMLimit;
    }

    public int getLeadPMLimit() {
        return leadPMLimit;
    }

    public void setLeadPMLimit(int leadPMLimit) {
        this.leadPMLimit = leadPMLimit;
    }

    public List<Weeks> getAllRegWeeks() {
        return allRegWeeks;
    }

    public void setAllRegWeeks(List<Weeks> allRegWeeks) {
        this.allRegWeeks = allRegWeeks;
    }

    public Date getEarly_cutoff() {
        return early_cutoff;
    }

    public void setEarly_cutoff(Date early_cutoff) {
        this.early_cutoff = early_cutoff;
    }

    public String getAmTimeStart() {
        return amTimeStart;
    }

    public void setAmTimeStart(String amTimeStart) {
        this.amTimeStart = amTimeStart;
    }

    public String getAmTimeEnd() {
        return amTimeEnd;
    }

    public void setAmTimeEnd(String amTimeEnd) {
        this.amTimeEnd = amTimeEnd;
    }

    public String getAmECTimeStart() {
        return amECTimeStart;
    }

    public void setAmECTimeStart(String amECTimeStart) {
        this.amECTimeStart = amECTimeStart;
    }

    public String getAmECTimeEnd() {
        return amECTimeEnd;
    }

    public void setAmECTimeEnd(String amECTimeEnd) {
        this.amECTimeEnd = amECTimeEnd;
    }

    public String getPmTimeStart() {
        return pmTimeStart;
    }

    public void setPmTimeStart(String pmTimeStart) {
        this.pmTimeStart = pmTimeStart;
    }

    public String getPmTimeEnd() {
        return pmTimeEnd;
    }

    public void setPmTimeEnd(String pmTimeEnd) {
        this.pmTimeEnd = pmTimeEnd;
    }

    public String getFullTimeStart() {
        return fullTimeStart;
    }

    public void setFullTimeStart(String fullTimeStart) {
        this.fullTimeStart = fullTimeStart;
    }

    public String getFullTimeEnd() {
        return fullTimeEnd;
    }

    public void setFullTimeEnd(String fullTimeEnd) {
        this.fullTimeEnd = fullTimeEnd;
    }

    public String getFullECTimeStart() {
        return fullECTimeStart;
    }

    public void setFullECTimeStart(String fullECTimeStart) {
        this.fullECTimeStart = fullECTimeStart;
    }

    public String getFullECTimeEnd() {
        return fullECTimeEnd;
    }

    public void setFullECTimeEnd(String fullECTimeEnd) {
        this.fullECTimeEnd = fullECTimeEnd;
    }

    public String getPmECTimeStart() {
        return pmECTimeStart;
    }

    public void setPmECTimeStart(String pmECTimeStart) {
        this.pmECTimeStart = pmECTimeStart;
    }

    public String getPmECTimeEnd() {
        return pmECTimeEnd;
    }

    public void setPmECTimeEnd(String pmECTimeEnd) {
        this.pmECTimeEnd = pmECTimeEnd;
    }

    public String getAmSelected() {
        return amSelected;
    }

    public void setAmSelected(String amSelected) {
        this.amSelected = amSelected;
    }

    public String getAmECSelected() {
        return amECSelected;
    }

    public void setAmECSelected(String amECSelected) {
        this.amECSelected = amECSelected;
    }

    public String getPmSelected() {
        return pmSelected;
    }

    public void setPmSelected(String pmSelected) {
        this.pmSelected = pmSelected;
    }

    public String getPmECSelected() {
        return pmECSelected;
    }

    public void setPmECSelected(String pmECSelected) {
        this.pmECSelected = pmECSelected;
    }

    public String getFullSelected() {
        return fullSelected;
    }

    public void setFullSelected(String fullSelected) {
        this.fullSelected = fullSelected;
    }

    public String getFullECSelected() {
        return fullECSelected;
    }

    public void setFullECSelected(String fullECSelected) {
        this.fullECSelected = fullECSelected;
    }
}
