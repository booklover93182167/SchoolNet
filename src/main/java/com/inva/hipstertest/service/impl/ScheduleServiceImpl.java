package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.domain.Schedule;
import com.inva.hipstertest.repository.PupilRepository;
import com.inva.hipstertest.repository.ScheduleRepository;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import com.inva.hipstertest.service.mapper.ScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        //log.debug(scheduleRepository.findAllByFormId(1L).toString());
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
     * Get all schedules by requested date and current user form id.
     *
     * @param date requested date
     * @return the list of entities.
     */
    @Override
    public List<ScheduleDTO> findAllByFormIdAndDate(String date) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, timeFormatter);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime dateStart = localDateTime.atZone(zoneId);
        ZonedDateTime dateEnd = dateStart.plusDays(1);

        Pupil currentPupil = pupilRepository.findPupilByCurrentUser();
        log.debug("Request to get schedules by pupil form and date {}", date);
        List<Schedule> schedules = scheduleRepository.findAllMembersByFormIdAndDateBetween(currentPupil.getForm().getId(), dateStart, dateEnd);
        List<ScheduleDTO> scheduleDTOS = scheduleMapper.schedulesToScheduleDTOs(schedules);
        List<ScheduleDTO> scheduleToSend = buildScheduleForDayWithEmptyRecords(scheduleDTOS);
        return scheduleToSend;
    }

    private List<ScheduleDTO> buildScheduleForDayWithEmptyRecords(List<ScheduleDTO> list) {
        List<ScheduleDTO> resultList = new ArrayList<>();
        if (list.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                ScheduleDTO emptyScheduleDTO = createEmptyScheduleDTO();
                emptyScheduleDTO.setLessonPosition(i);
                resultList.add(emptyScheduleDTO);
            }
        } else {
            int lessonPositions = 1;
            do {
                boolean flag = false;
                for (ScheduleDTO schedule : list) {
                    if (schedule.getLessonPosition() == lessonPositions) {
                        resultList.add(schedule);
                        lessonPositions++;
                        flag = true;
                    }
                }
                if (!flag) {
                    ScheduleDTO emptyScheduleDTO = createEmptyScheduleDTO();
                    emptyScheduleDTO.setLessonPosition(lessonPositions++);
                    resultList.add(emptyScheduleDTO);
                }
            } while (lessonPositions <= 10);
        }
        return resultList;
    }

    private ScheduleDTO createEmptyScheduleDTO() {
        ScheduleDTO emptyScheduleDTO = new ScheduleDTO();
        emptyScheduleDTO.setLessonName("-");
        emptyScheduleDTO.setHomework("-");
        emptyScheduleDTO.setTeacherFirstName("");
        emptyScheduleDTO.setTeacherLastName("-");
        emptyScheduleDTO.setClassroomName("-");
        return emptyScheduleDTO;
    }

    /**
     * Get all the schedules by school id.
     *
     * @param schoolId the id of the school
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDTO> findAllBySchoolId(Long schoolId) {
        log.debug("Request to get all Schedules by school id : {}", schoolId);
        //log.debug(scheduleRepository.findAllByFormId(1L).toString());
        List<ScheduleDTO> result = scheduleRepository.findAllBySchoolId(schoolId).stream()
            .map(scheduleMapper::scheduleToScheduleDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    @Override
    public List<ScheduleDTO> findAllByTeacherIdGroupByFormIdAndLessonId(Long teacherId) {
        log.debug("Request to get distinct schedules for teacher {}", teacherId);
        List<Schedule> formsAndLessons = scheduleRepository.findAllByTeacherIdGroupByFormIdAndLessonId(teacherId);
        List<ScheduleDTO> formsAndLessonsDTOs = scheduleMapper.schedulesToScheduleDTOs(formsAndLessons);
        return formsAndLessonsDTOs;
    }

}
