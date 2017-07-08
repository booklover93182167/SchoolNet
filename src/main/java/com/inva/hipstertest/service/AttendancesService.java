package com.inva.hipstertest.service;

import com.inva.hipstertest.service.dto.AttendancesDTO;

import java.util.List;

/**
 * Service Interface for managing Attendances.
 */
public interface AttendancesService {

    /**
     * Save a attendances.
     *
     * @param attendancesDTO the entity to save
     * @return the persisted entity
     */
    AttendancesDTO save(AttendancesDTO attendancesDTO);

    /**
     * Get all the attendances.
     *
     * @return the list of entities
     */
    List<AttendancesDTO> findAll();

    /**
     * Get the "id" attendances.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AttendancesDTO findOne(Long id);

    /**
     * Delete the "id" attendances.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Find all by pupil id and lesson id.
     *
     * @param pupilId the id of the pupil
     * @param lessonId the id of the lesson
     * @return list of entities
     */
    List<AttendancesDTO> findAllByPupilIdAndLessonId(Long pupilId, Long lessonId);

    /**
     * Find all by pupil id and exact date.
     *
     * @param date requested date
     * @return list of entities
     */
    List<AttendancesDTO> findAllByPupilIdAndExactDate(Long pupilId, String date);

    /**
     * Find all by course Id.
     *
     * @param courseId the id of the course
     * @return list of entities
     */
    List<AttendancesDTO> findAllByCourseId(Long courseId);

}
