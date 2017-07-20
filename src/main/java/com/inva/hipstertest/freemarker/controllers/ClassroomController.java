package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.freemarker.searchcriteria.SearchCriteria;
import com.inva.hipstertest.service.ClassroomService;
import com.inva.hipstertest.service.dto.ClassroomDTO;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClassroomController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private ClassroomService classroomService;


    /**
     * Request to get all classrooms from user school.
     *
     * @return list of classroomDTOs
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/classrooms", method = RequestMethod.GET)
    public @ResponseBody List<ClassroomDTO> getAllClassroomsFromCurrentSchool(){
        log.debug("Create Ajax request for all classrooms");
        return classroomService.findAllByCurrentSchool();
    }

    /**
     * Request to get available classrooms by search options.
     *
     * @param searchCriteria Search options
     * @return list of classroomDTOs
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/classrooms-wp", method = RequestMethod.POST)
    public @ResponseBody
    List<ClassroomDTO> getAvailableClassroomBySearchCriteria(@RequestBody SearchCriteria searchCriteria){
        log.debug("Create Ajax request to search available forms by search criteria");
        Validate.notNull(searchCriteria.getLessonPosition(), "Field 'lessonPosition' on searchCriteria can not be null.");
        Validate.notNull(searchCriteria.getDate(), "Field 'Date' on  searchCriteria can not be null.");
        if (searchCriteria.getId() != null) {
            return classroomService.findAvailablePlusOneById(searchCriteria);
        }
        return classroomService.findAvailableByCurrentSchoolAndSearchCriteria(searchCriteria);
    }

}
