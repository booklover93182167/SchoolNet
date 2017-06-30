package com.inva.hipstertest.service;

import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.domain.Teacher;
import com.inva.hipstertest.service.dto.SchoolDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Service Interface for managing School.
 */
public interface SchoolService {

    /**
     * Save a school.
     *
     * @param schoolDTO the entity to save
     * @return the persisted entity
     */
    SchoolDTO save(SchoolDTO schoolDTO);

    /**
     *  Get all the schools.
     *
     *  @return the list of entities
     */
    List<SchoolDTO> findAll();

    /**
     *  Get the "id" school.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SchoolDTO findOne(Long id);

    /**
     *  Delete the "id" school.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);



    /**
     *  Find all headTeacher of current schhol .
     *
     *
     */
    List<TeacherDTO> findHeadTeachersOfSchool(Long id);




    /**
     +     *FOR PAGEABLE
     +     * @param pageable
     +     * @return
     +     */
    Page<SchoolDTO> findAll(Pageable pageable);



    Page<SchoolDTO> findAllEnabled(Pageable pageable);

    Page<SchoolDTO> findAllDisabled(Pageable pageable);


     /**
     *FOR PAGEABLE
     * @return
     */
     Long countAllEnabledSchools();

    /**
     *FOR PAGEABLE
     * @return
     */
    Long countAllDisabledSchools();

  
}
