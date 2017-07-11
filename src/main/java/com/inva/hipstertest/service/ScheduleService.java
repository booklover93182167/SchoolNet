package com.inva.hipstertest.service;

import com.inva.hipstertest.freemarker.searchcriteria.ScheduleSearchCriteria;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Interface for managing Schedule.
 */
public interface ScheduleService {

    /**
     * Save a schedule.
     *
     * @param scheduleDTO the entity to save
     * @return the persisted entity
     */
    ScheduleDTO save(ScheduleDTO scheduleDTO);

    /**
     * Get all the schedules.
     *
     * @return the list of entities
     */
    List<ScheduleDTO> findAll();

    /**
     * Get the "id" schedule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ScheduleDTO findOne(Long id);

    /**
     * Delete the "id" schedule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all Schedules by form ID
     *
     * @param id form Id of Pupil
     * @return the list of entities
     */
    List<ScheduleDTO> findAllByFormId(Long id);

    /**
     * Get all schedules by teacher ID ordered by Date
     *
     * @param teacherId teacher id
     * @return the list of entities
     */
    List<ScheduleDTO> findAllByTeacherIdOrderByDate(Long teacherId);

    /**
     * Get all the schedules by school id.
     *
     * @param schoolId the id of the school
     * @return the list of entities
     */
    List<ScheduleDTO> findAllBySchoolId(Long schoolId);

    /**
     * Get page with schedules by courseId and maxDate.
     *
     * @param pageable pageable object
     * @param courseId the id of the course
     * @param maxDate top limit date
     * @return page with schedules
     */
    Page<ScheduleDTO> findAllByCourseIdAndMaxDate(Pageable pageable, Long courseId, ZonedDateTime maxDate);

    /**
     * Count all schedules by courseId and maxDate.
     *
     * @param courseId the id of the course
     * @param maxDate top limit date
     * @return number of schedules
     */
    Long countAllByCourseIdAndMaxDate(Long courseId, ZonedDateTime maxDate);

    /**
     * Get all the schedules by form id and exact date.
     *
     * @param formId the id of the form
     * @param date requested date
     * @return the list of entities
     */
    List<ScheduleDTO> findAllByFormIdAndExactDate(Long formId, String date);

    /**
     * Get all the schedules by form id and date between.
     *
     * @param formId the id of the form
     * @param startDate first date
     * @param endDate last date
     * @return the list of entities
     */
    List<ScheduleDTO> findAllByFormIdAndDateBetween(Long formId, ZonedDateTime startDate, ZonedDateTime endDate);

    /**
     * Get all schedules by search criteria.
     *
     * @param scheduleSearchCriteria search options
     * @return the list of entities
     */
    List<ScheduleDTO> getScheduleBySearchCriteria(ScheduleSearchCriteria scheduleSearchCriteria);
}
