package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.domain.Schedule;
import com.inva.hipstertest.freemarker.searchcriteria.ScheduleSearchCriteria;
import com.inva.hipstertest.repository.PupilRepository;
import com.inva.hipstertest.repository.ScheduleRepository;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import com.inva.hipstertest.service.mapper.ScheduleMapper;
import com.inva.hipstertest.service.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Schedule.
 */
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    private final ScheduleRepository scheduleRepository;

    private final PupilRepository pupilRepository;

    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, PupilRepository pupilRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.pupilRepository = pupilRepository;
        this.scheduleMapper = scheduleMapper;
    }

    /**
     * Save a schedule.
     *
     * @param scheduleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        log.debug("Request to save Schedule : {}", scheduleDTO);
        Schedule schedule = scheduleMapper.scheduleDTOToSchedule(scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        ScheduleDTO result = scheduleMapper.scheduleToScheduleDTO(schedule);
        return result;
    }

    /**
     * Get all the schedules.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDTO> findAll() {
        log.debug("Request to get all Schedules");
        List<ScheduleDTO> result = scheduleRepository.findAll().stream()
            .map(scheduleMapper::scheduleToScheduleDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     * Get one schedule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ScheduleDTO findOne(Long id) {
        log.debug("Request to get Schedule : {}", id);
        Schedule schedule = scheduleRepository.findOne(id);
        ScheduleDTO scheduleDTO = scheduleMapper.scheduleToScheduleDTO(schedule);
        return scheduleDTO;
    }

    /**
     * Delete the  schedule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Schedule : {}", id);
        scheduleRepository.delete(id);
    }

    @Override
    public List<ScheduleDTO> findAllByFormId(Long id) {
        log.debug("Request to get schedules for form {}", id);
        List<ScheduleDTO> schedules = scheduleRepository.findByFormId(id).stream()
            .map(scheduleMapper::scheduleToScheduleDTO).collect(Collectors.toCollection(LinkedList::new));
        return schedules;
    }

    /**
     * Get all schedules by teacher id.
     *
     * @param id the teacher id
     * @return the list of entities ordered by date
     */
    @Override
    public List<ScheduleDTO> findAllByTeacherIdOrderByDate(Long id) {
        log.debug("Request to get schedules for teacher {}", id);
        List<Schedule> schedules = scheduleRepository.findAllByTeacherIdOrderByDate(id);
        List<ScheduleDTO> scheduleDTOS = scheduleMapper.schedulesToScheduleDTOs(schedules);
        return scheduleDTOS;
    }

    /**
     * Get all the schedules by school id.
     *
     * @param schoolId the id of the school
     * @return the list of entities
     */
    @Override
    public List<ScheduleDTO> findAllBySchoolId(Long schoolId) {
        log.debug("Request to get all Schedules by school id : {}", schoolId);
        List<ScheduleDTO> result = scheduleRepository.findAllBySchoolId(schoolId).stream()
            .map(scheduleMapper::scheduleToScheduleDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     * Get page with schedules by courseId and maxDate.
     *
     * @param pageable pageable object
     * @param courseId the id of the course
     * @param maxDate top limit date
     * @return page with schedules
     */
    @Override
    public Page<ScheduleDTO> findAllByCourseIdAndMaxDate(Pageable pageable, Long courseId, ZonedDateTime maxDate) {
        log.debug("Request to get schedules by courseId {} and max date {}", courseId, maxDate);
        return scheduleRepository.findAllByCourseIdAndMaxDate(pageable, courseId, maxDate).map(scheduleMapper::scheduleToScheduleDTO);
    }

    /**
     * Count all schedules by courseId and maxDate.
     *
     * @param courseId the id of the course
     * @param maxDate top limit date
     * @return number of schedules
     */
    @Override
    public Long countAllByCourseIdAndMaxDate(Long courseId, ZonedDateTime maxDate) {
        log.debug("Request to count schedules by courseId {} and max date {}", courseId, maxDate);
        return scheduleRepository.countAllByCourseIdAndMaxDate(courseId, maxDate);
    }

    /**
     * Get all the schedules by form id and exact date.
     *
     * @param formId the id of the form
     * @param date requested date
     * @return the list of entities
     */
    @Override
    public List<ScheduleDTO> findAllByFormIdAndExactDate(Long formId, String date) {
        log.debug("Request to get schedules by formId {} and exact date {}", formId, date);
        ZonedDateTime dateStart = DataUtil.getZonedDateTime(date);
        ZonedDateTime dateEnd = dateStart.plusDays(1);
        List<Schedule> schedules = scheduleRepository.findAllByFormIdAndDateBetween(formId, dateStart, dateEnd);
        List<ScheduleDTO> scheduleDTOS = scheduleMapper.schedulesToScheduleDTOs(schedules);
        return scheduleDTOS;
    }

    /**
     * Get all the schedules by form id and date between.
     *
     * @param formId the id of the form
     * @param startDate first date
     * @param endDate last date
     * @return the list of entities
     */
    @Override
    public List<ScheduleDTO> findAllByFormIdAndDateBetween(Long formId, ZonedDateTime startDate, ZonedDateTime endDate) {
        log.debug("Request to get schedules by formId {} and date between {} and {}", formId, startDate, endDate);
        List<Schedule> schedules = scheduleRepository.findAllByFormIdAndDateBetween(formId, startDate, endDate);
        List<ScheduleDTO> scheduleDTOS = scheduleMapper.schedulesToScheduleDTOs(schedules);
        return scheduleDTOS;
    }

    /**
     * Get all schedules by searching parameters.
     *
     * @param scheduleSearchCriteria searching parameters
     * @return the list of entities.
     */
    @Override
    public List<ScheduleDTO> getScheduleBySearchCriteria(ScheduleSearchCriteria scheduleSearchCriteria) {
        ZonedDateTime date = DataUtil.getZonedDateTime(scheduleSearchCriteria.getDate());
        ZonedDateTime lastMonday = date.with(ChronoField.DAY_OF_WEEK, 1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime nextMonday = lastMonday.toLocalDate().atStartOfDay(zoneId).plusWeeks(1);

        List<Schedule> schedules;
        Long id = scheduleSearchCriteria.getId();
        switch (scheduleSearchCriteria.getScheduleType()) {
            case BY_FORM:
                schedules = scheduleRepository.findAllMembersByFormIdAndDateBetween(id, lastMonday, nextMonday);
                break;
            case BY_TEACHER:
                schedules = scheduleRepository.findAllByTeacherIdAndDateBetween(id, lastMonday, nextMonday);
                break;
            case BY_CLASSROOM:
                schedules = scheduleRepository.findAllMembersByClassroomIdAndDateBetween(id, lastMonday, nextMonday);
                break;
            default:
                throw new RuntimeException("invalid schedule type");
        }
        return scheduleMapper.schedulesToScheduleDTOs(schedules);
    }
}
