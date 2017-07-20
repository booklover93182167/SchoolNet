package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.freemarker.searchcriteria.ScheduleSearchCriteria;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ScheduleController {

    private static final String ENTITY_NAME = "schedule";

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Request to get schedules by search options.
     *
     * @param criteria search options
     * @return list of Schedule DTOs
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/schedule", method = RequestMethod.POST)
    public @ResponseBody
    List<ScheduleDTO> getScheduleBySearchCriteria(@RequestBody ScheduleSearchCriteria criteria) {
        log.debug("Create Ajax request to search schedule by search criteria");
        Validate.notNull(criteria.getId(), "Field 'id' on scheduleSearchCriteria can not be null.");
        Validate.notNull(criteria.getScheduleFilterType(), "Field 'Schedule type' on scheduleSearchCriteria can not be null.");
        Validate.notNull(criteria.getDate(), "Field 'Date' on scheduleSearchCriteria can not be null.");
        return scheduleService.getScheduleBySearchCriteria(criteria);
    }

    /**
     * Get schedule by id.
     *
     * @param scheduleId schedule id
     * @return the Schedule DTO.
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/schedule/{scheduleId}", method = RequestMethod.GET)
    public @ResponseBody ScheduleDTO getSchedulesById(@PathVariable("scheduleId") Long scheduleId) {
        log.debug("Request to get schedule by id : {}", scheduleId);
        return scheduleService.findOne(scheduleId);
    }

    /**
     * Save new schedule.
     *
     * @param scheduleDTO the scheduleDTO to create
     * @return stored schedule DTO
     */
    @PostMapping("freemarker/teacher-mgmt/schedule-mgmt/schedule-create")
    public @ResponseBody ScheduleDTO createNewSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.debug("request to save new Schedule : {}", scheduleDTO);
        ScheduleDTO result = scheduleService.save(scheduleDTO);
        if (result != null) {
            result = scheduleService.findOne(result.getId());
        }
        return result;
    }

    /**
     * Delete schedule by id.
     *
     * @param scheduleId schedule id
     * @return the Schedule DTO.
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/schedule-delete/{scheduleId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        log.debug("Request to delete schedule by id : {}", scheduleId);
        scheduleService.delete(scheduleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
