package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("select s from Schedule s where s.course.form.id = :formId")
    List<Schedule> findByFormId(@Param("formId") Long formId);

    @Query("select s from Schedule s where s.enabled = true and s.course.teacher.id = :teacherId order by s.date")
    List<Schedule> findAllByTeacherIdOrderByDate(@Param("teacherId") Long teacherId);

    @Query("select schedule from Schedule schedule, Teacher teacher, School school where schedule.course.teacher.id = teacher.id and teacher.school.id = school.id and school.id = :schoolId")
    List<Schedule> findAllBySchoolId(@Param("schoolId") Long schoolId);

    @Query("select s from Schedule s where s.course.form.id = :formId and s.date between :startDate and :endDate")
    List<Schedule> findAllMembersByFormIdAndDateBetween(@Param("formId") Long formId, @Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

    @Query("select s from Schedule s where s.enabled = true and s.course.teacher.id = :teacherId and s.course.form.id = :formId and s.course.lesson.id = :lessonId and s.date <= :today order by s.date")
    Page<Schedule> findSchedulesByTeacherIdFormIdSubjectIdMaxDate(Pageable pageable, @Param("teacherId") Long teacherId, @Param("formId") Long formId, @Param("lessonId") Long lessonId, @Param("today") ZonedDateTime today);

    @Query("select count(s) from Schedule s where s.enabled = true and s.course.teacher.id = :teacherId and s.course.form.id = :formId and s.course.lesson.id = :lessonId and s.date <= :today order by s.date")
    Long countSchedulesForGradeBook(@Param("teacherId") Long teacherId, @Param("formId") Long formId, @Param("lessonId") Long lessonId, @Param("today") ZonedDateTime today);

}
