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
    List<Lesson> getAllLessonsByTeacherId(@Param("teacherId") Long teacherId);
}
