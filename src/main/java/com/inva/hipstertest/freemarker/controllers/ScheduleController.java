package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.freemarker.searchcriteria.ScheduleSearchCriteria;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScheduleController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Request to get schedules by search options.
     *
     * @param scheduleSearchCriteria search options
     * @return list of Schedule DTOs
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/schedule", method = RequestMethod.POST)
    public @ResponseBody
    List<ScheduleDTO> getScheduleBySearchCriteria(@RequestBody ScheduleSearchCriteria scheduleSearchCriteria){
        log.debug("Create Ajax request to search schedule by search criteria");
        Validate.notNull(scheduleSearchCriteria.getId(), "Field 'id' on scheduleSearchCriteria can not be null.");
        Validate.notNull(scheduleSearchCriteria.getScheduleFilterType(), "Field 'Schedule type' on scheduleSearchCriteria can not be null.");
        Validate.notNull(scheduleSearchCriteria.getDate(), "Field 'Date' on scheduleSearchCriteria can not be null.");
        return scheduleService.getScheduleBySearchCriteria(scheduleSearchCriteria);
    }

    /**
     * Get schedule by id.
     *
     * @param scheduleId schedule id
     * @return the Schedule DTO.
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/schedule/{scheduleId}", method = RequestMethod.GET)
    public @ResponseBody ScheduleDTO getSchedulesById(@PathVariable("scheduleId") Long scheduleId) {
        log.debug("Request to get schedule by id : {}", scheduleId);
        return scheduleService.findOne(scheduleId);
    }
}
