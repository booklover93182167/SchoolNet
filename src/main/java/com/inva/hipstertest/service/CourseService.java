package com.inva.hipstertest.service;

import com.inva.hipstertest.service.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Course.
 */
public interface CourseService {

    /**
     * Save a course.
     *
     * @param courseDTO the entity to save
     * @return the persisted entity
     */
    CourseDTO save(CourseDTO courseDTO);

    /**
     *  Get all the courses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CourseDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" course.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CourseDTO findOne(Long id);

    /**
     *  Delete the "id" course.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the courses by form id.
     *
     * @param formId the id of the form
     * @return the list of entities
     */
    List<CourseDTO> findAllByFormId(Long formId);

    /**
     * Get all the courses by teacher id.
     *
     * @param teacherId the id of the teacher
     * @return the list of entities
     */
    List<CourseDTO> findAllByTeacherId(Long teacherId);

    /**
     * Get one course by form id, lesson id, teacher id.
     *
     * @param formId the id of the form
     * @param lessonId the id of the lesson
     * @param teacherId the id of the teacher
     * @return one course
     */
    CourseDTO findOneByFormIdLessonIdTeacherId(Long formId, Long lessonId, Long teacherId);

}
