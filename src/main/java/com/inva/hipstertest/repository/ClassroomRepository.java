package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Classroom;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the Classroom entity.
 */
@SuppressWarnings("unused")
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    @Query("select t from Classroom t where t.school.id =:id")
    List<Classroom> findAllClassroomsByCurrentSchool(@Param("id") long id);

    @Query("select classroom from Classroom classroom where classroom.enabled = true " +
        "and classroom.school.id = :schoolId and classroom.id not in (select classroom.id " +
        "from Schedule schedule where schedule.lessonPosition = :lessonPosition and schedule.date = :date)")
    List<Classroom> findAllAvailableClassroomsBySchoolIdAndSearchCriteria(@Param("schoolId") Long schoolId,
                                                                          @Param("lessonPosition") Integer lessonPosition,
                                                                          @Param("date") ZonedDateTime date);
}
