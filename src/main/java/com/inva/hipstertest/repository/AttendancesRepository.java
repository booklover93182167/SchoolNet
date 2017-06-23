package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Attendances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the Attendances entity.
 */
@SuppressWarnings("unused")
public interface AttendancesRepository extends JpaRepository<Attendances, Long> {

    @Query("select attendances from Attendances attendances join attendances.schedule schedule" +
        " where attendances.enabled = true and attendances.pupil.id = :pupilId and schedule.date between :startDate and :endDate")
    List<Attendances> findAllMembersByPupilIdAndDateBetween(@Param("pupilId") Long pupilId, @Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

    //query to find all attendances for lesson and pupil
    @Query(value = "select * from attendances where pupil_id =:pupilId and\n" +
        "schedule_id in(select id from schedule where lesson_id =:lessonId)", nativeQuery = true)
    List<Attendances> findAllByPupilAndLessonId(@Param("pupilId") Long pupilId, @Param("lessonId") Long lessonId);
}
