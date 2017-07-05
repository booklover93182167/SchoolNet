package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Course;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Course entity.
 */
@SuppressWarnings("unused")
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select c from Course c where c.enabled = true and c.form.id = :formId")
    List<Course> findAllByFormId(@Param("formId") Long formId);

    @Query("select c from Course c where c.enabled = true and c.teacher.id = :teacherId")
    List<Course> findAllByTeacherId(@Param("teacherId") Long teacherId);

}
