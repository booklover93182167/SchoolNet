package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Lesson;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Lesson entity.
 */
@SuppressWarnings("unused")
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select lessons from Lesson lessons join lessons.teachers teacher where teacher.id =:teacherId")
    List<Lesson> findAllByTeacherId(@Param("teacherId") Long teacherId);

    @Query("select distinct lesson from Lesson lesson join lesson.schedules schedule where schedule.form.id = :formId")
    List<Lesson> getDistinctLessonsByFormId(@Param("formId") Long formId);

}
