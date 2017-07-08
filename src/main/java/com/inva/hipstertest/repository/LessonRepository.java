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

    @Query("select l from Lesson l where l.id in (select distinct c.lesson.id from Course c where c.teacher.id = :teacherId)")
    List<Lesson> findAllByTeacherId(@Param("teacherId") Long teacherId);

    @Query("select l from Lesson l where l.id in (select distinct c.lesson.id from Course c where c.form.id = :formId)")
    List<Lesson> findAllByFormId(@Param("formId") Long formId);

}
