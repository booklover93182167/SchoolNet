package com.inva.hipstertest.repository;


import com.inva.hipstertest.domain.School;

import com.inva.hipstertest.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the School entity.
 */
@SuppressWarnings("unused")
public interface SchoolRepository extends JpaRepository<School, Long> {


    @Query("select t from Teacher t left join  t.user where(t.school.id = :id and 'ROLE_HEAD_TEACHER' in elements(t.user.authorities)) ")
    List<Teacher> findHeadTeachersOfSchool(@Param("id") Long id);

    @Query("select s from School s where s.enabled = true")
    Page<School> findAllEnabled(Pageable pageable);


    @Query("select s from School s where s.enabled = false")
    Page<School> findAllDisabled(Pageable pageable);

    @Query("SELECT COUNT(s) FROM School s where s.enabled = true")
    Long countAllEnabledSchools();

    @Query("SELECT COUNT(s) FROM School s where s.enabled = false")
    Long countAllDisabledSchools();

    @Query(value = " SELECT school.id FROM schoolnet.school\n" +
        "join form on form.school_id=school.id\n" +
        "where form.id=:formId", nativeQuery = true)
    Long getSchoolIdByForm(@Param("formId") Long formId);

}
