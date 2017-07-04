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

    @Query("select c from Course c where c.enabled = true and c.teacher.id = :teacherId")
    List<Course> findByTeacherId(@Param("teacherId") Long teacherId);

}
