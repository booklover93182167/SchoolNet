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

    @Query(value = "select scd.homework from schedule as scd " +
        "where scd.enabled = 1 and scd.lesson_id =: lessonId and scd.form_id =: formId " +
        "and date(scd.jhi_date) <  curdate() order by scd.jhi_date desc limit 1", nativeQuery = true)
    String getLastHomeworkByLessonIdAndByFormId(@Param("lessonId") Long lessonId, @Param("formId") Long formId);

    @Query(value = "select lsn.* from lesson as lsn " +
        "join teacher_lesson as tlsn on lsn.id = tlsn.lessons_id " +
        "where lsn.enabled = 1 and tlsn.teachers_id =: teacherId", nativeQuery = true)
    List<Lesson> getAllLessonsByTeacherId(@Param("teacherId") Long teacherId);
}
