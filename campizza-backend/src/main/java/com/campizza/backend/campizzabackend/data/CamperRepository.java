package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface CamperRepository extends JpaRepository<Camper, Long> {
    List<Camper> findByUser_Id(Long id);

    List<Camper> findByGroup(String group);

    Optional<Camper> findById(Long id);

    Boolean existsByFirstNameAndUser(String name, User user) ;

    Optional<Camper> findByFirstNameAndUser(String name, User user);

    Boolean existsByUser(User user);

    Optional<Camper> findCamperByUser_Id_AndFirstName(Long id, String firstName);

    Optional<Camper> findCamperByUser_Id_AndId(Long uid, Long cid);

    Optional<Camper> findCamperById(Long id);

    void deleteCamperById(Long id);




    @Query(value =
                    "select c.id from campers c " +
                    "where c.id in (select ac.camper_id from week_campers_am ac where ac.week_id in " +
                        "(select r.all_reg_weeks_week_id from registered_weeks_all_reg_weeks r where r.registered_weeks_id in (select p.id from registered_weeks p where p.current_year = :year)) " +
                    "union select eac.camper_id from early_week_campers_am eac where eac.week_id in " +
                        "(select r.all_reg_weeks_week_id from registered_weeks_all_reg_weeks r where r.registered_weeks_id in (select p.id from registered_weeks p where p.current_year = :year)) " +
                    "union select pc.camper_id from week_campers_pm pc where pc.week_id in " +
                        "(select r.all_reg_weeks_week_id from registered_weeks_all_reg_weeks r where r.registered_weeks_id in (select p.id from registered_weeks p where p.current_year = :year)) " +
                    "union select epc.camper_id from early_week_campers_pm epc where epc.week_id in " +
                        "(select r.all_reg_weeks_week_id from registered_weeks_all_reg_weeks r where r.registered_weeks_id in (select p.id from registered_weeks p where p.current_year = :year)))", nativeQuery = true
    )
    List<Long> findAllCampersRegisteredByYear(Integer year);

    @Query(value =
            "select c.id from campers c, week_campers_am wc where c.id in (select wc.camper_id where wc.week_id =:weekID)", nativeQuery = true
    )

    List<Long> findAllAMCampersRegisteredById(Long weekID);

    @Query(value =
            "select c.id from campers c, early_week_campers_am wc where c.id in (select wc.camper_id where wc.week_id =:weekID)", nativeQuery = true
    )
    List<Long> findAllAMEarlyCampersRegisteredById(Long weekID);

    @Query(value =
            "select c.id from campers c, week_campers_pm wc where c.id in (select wc.camper_id where wc.week_id =:weekID)", nativeQuery = true
    )
    List<Long> findAllPMCampersRegisteredById(Long weekID);

    @Query(value =
            "select c.id from campers c, early_week_campers_pm wc where c.id in (select wc.camper_id where wc.week_id =:weekID)", nativeQuery = true
    )
    List<Long> findAllPMEarlyCampersRegisteredById(Long weekID);



    @Query(value = "select * from campers c where " +
            "c.group_name = :name and c.id in " +
            "(select ac.camper_id from attendance_campers ac where " +
            "ac.attendance_id in (select a.id from attendance a where " +
            " a.date = substring(:date,1,10) and a.pm = true))", nativeQuery = true
    )
    @Nullable
    List<Camper> findAllCampersAttendingByPMGroup(String name, Date date);


    @Query(value = "select * from campers c where " +
            "c.group_name = :name and c.id in " +
            "(select ac.camper_id from attendance_campers ac where " +
            "ac.attendance_id in (select a.id from attendance a where " +
            " a.date = substring(:date,1,10) and a.am = true))", nativeQuery = true
    )
    @Nullable
    List<Camper> findAllCampersAttendingByAMGroup(String name, Date date);



    @Query(value = "select * from campers c where " +
                    "c.group_name = :name and c.id in " +
                    "(select wc.camper_id from week_campers_am wc where " +
                    "wc.week_id in (select w.week_id from weeks w where " +
                    ":date between w.start_of_week and " +
                    "w.end_of_week))", nativeQuery = true
    )
    @Nullable
    List<Camper> findAllAMCampersRegByGroup(String name, Date date);

    @Query(value = "select * from campers c where " +
            "c.group_name = :name and c.id in " +
            "(select wc.camper_id from week_campers_pm wc where " +
            "wc.week_id in (select w.week_id from weeks w where " +
            ":date between w.start_of_week and " +
            "w.end_of_week))", nativeQuery = true
    )
    @Nullable
    List<Camper> findAllPMCampersRegByGroup(String name, Date date);


    @Query(value = "select * from campers c ", nativeQuery = true)
    @Nullable
    List<Camper> findAllCampersIfExist();


}

// select * from weeks where "2020-06-08" between weeks.start_of_week and weeks.end_of_week
//
//interface CamperIDAndWeekID {
//    Long getId();
//    String getName();
//}