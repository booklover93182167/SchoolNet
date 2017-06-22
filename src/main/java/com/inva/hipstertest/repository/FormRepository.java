package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Form entity.
 */
@SuppressWarnings("unused")
public interface FormRepository extends JpaRepository<Form, Long> {

    @Query("select distinct forms from Form forms join forms.schedules schedules where schedules.teacher.id =:teacherId")
    List<Form> findAllByTeacherId(@Param("teacherId") Long teacherId);

    @Query("select t from Form t where t.school.id =:id")
    List<Form> findAllFormsByCurrentSchool(@Param("id") long id);

    @Query("select f from Form f where f.school.id =:id")
    List<Form> findAllUnassignedFormsByCurrentSchool(@Param("id") long id);
}
