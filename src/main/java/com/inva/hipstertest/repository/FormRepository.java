package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Form;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Form entity.
 */
@SuppressWarnings("unused")
public interface FormRepository extends JpaRepository<Form, Long> {

    @Query(value = "select distinct frm.* from lesson as lsn " +
        "join teacher_lesson as tlsn on lsn.id = tlsn.lessons_id " +
        "join schedule as scd on tlsn.lessons_id = scd.lesson_id " +
        "join form as frm on scd.form_id = frm.id " +
        "where tlsn.teachers_id =: teacherId and frm.enabled = 1", nativeQuery = true)
    List<Form> getAllByTeacherId(@Param("teacherId") Long teacherId);

}
