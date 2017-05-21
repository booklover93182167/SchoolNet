package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Pupil;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Pupil entity.
 */
@SuppressWarnings("unused")
public interface PupilRepository extends JpaRepository<Pupil, Long> {

    @Query(value = "select pup.* from jhi_user as usr join pupil as pup on usr.id = pup.user_id" +
        "where pup.enabled = 1 and form_id =: formId", nativeQuery = true)
    List<Pupil> getAllByFormId(@Param("formId") Long formId);


}
