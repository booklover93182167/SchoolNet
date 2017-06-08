package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Classroom;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Classroom entity.
 */
@SuppressWarnings("unused")
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    @Query("select t from Classroom t where t.school.id =:id")
    List<Classroom> findAllClassroomsByCurrentSchool(@Param("id") long id);
}
