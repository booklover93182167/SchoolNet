package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the Schedule entity.
 */
@SuppressWarnings("unused")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select schedule from Schedule schedule where schedule.form.id = :formId")
    List<Schedule> findByFormId(@Param("formId") Long formId);

    @Query("select schedule from Schedule schedule where schedule.enabled = true and " +
        "schedule.teacher.id = :teacherId order by schedule.date")
    List<Schedule> findAllByTeacherIdOrderByDate(@Param("teacherId") Long teacherId);

    @Query("select schedule from Schedule schedule, Teacher teacher, School school where schedule.teacher.id = teacher.id and teacher.school.id = school.id and school.id = :schoolId")
    List<Schedule> findAllBySchoolId(@Param("schoolId") Long schoolId);

//    @Query(value = "SELECT * FROM Schedule schedule WHERE schedule.form_id = :formId AND " +
//        "YEAR(schedule.jhi_date) = YEAR (:date) AND MONTH(schedule.jhi_date) = MONTH (:date) AND DAY(schedule.jhi_date) = DAY (:date)", nativeQuery = true)
//    List<Schedule> findAllByFormIdAndDate(@Param("date") String date, @Param("formId") Long formId);

    @Query("select schedule from Schedule schedule where schedule.form.id = :formId and " +
        "schedule.date between :startDate and :endDate")
    List<Schedule> findAllMembersByFormIdAndDateBetween(@Param("formId") Long formId, @Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);
}
