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

    @Query("select distinct form from Form form left join form.schedules schedule" +
        " left join schedule.lesson lesson left join lesson.teachers teacher where teacher.id = :teacherId")
    List<Form> findAllByTeacherId(@Param("teacherId") Long teacherId);

}
