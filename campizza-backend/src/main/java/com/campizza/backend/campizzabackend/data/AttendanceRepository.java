package com.campizza.backend.campizzabackend.data;

import com.campizza.backend.campizzabackend.model.Attendance;
import com.campizza.backend.campizzabackend.model.Camper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByCurrDate(Date date);

    Optional<Attendance> findByCurrDateAndTimeAMAndTimePM(Date currDate, boolean timeAM, boolean timePM);

    @Nullable
    Optional<Attendance> findByCurrDateAndCampersIsContaining(Date currDate, Camper camper);

    Optional<Attendance> findByCurrDateAndBonusCampers(Date currDate, String camperName);

    List<Attendance> findAllByCampersIsContaining(Camper camper);


    @Query(value = "select a.am, a.pm, a.date, w.week_id " +
            "from attendance_campers ac, attendance a, weeks w " +
            "where ac.camper_id =:camperID and ac.attendance_id in " +
            "(select a.id " +
            "where a.date between  w.start_of_week and w.end_of_week " +
            "and w.week_id in " +
            "(select r.all_reg_weeks_week_id from registered_weeks_all_reg_weeks r " +
            "where r.registered_weeks_id in " +
            "(select p.id from registered_weeks p where p.current_year = :year)));"
    , nativeQuery = true)
    @Nullable
    List<Object[]> findAllAttendanceByCamperAndYear(Long camperID, Integer year);
}
