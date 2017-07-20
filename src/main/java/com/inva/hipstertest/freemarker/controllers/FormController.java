package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.freemarker.searchcriteria.FormSearchCriteria;
import com.inva.hipstertest.service.FormService;
import com.inva.hipstertest.service.dto.FormDTO;
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
public class FormController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private FormService formService;

    /**
     * Get all forms from current school.
     *
     * @return list of formDTOs
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/forms", method = RequestMethod.GET)
    public @ResponseBody
    List<FormDTO> getAllFormsFromCurrentSchool() {
        log.debug("Create Ajax request for all forms");
        return formService.findAllFormsByCurrentSchool();
    }

    /**
     * Request to get available forms to assign to Teacher
     *
     * @return available forms
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-get-av-forms", method = RequestMethod.GET)
    public @ResponseBody
    List<FormDTO> getAvailableForms() {
        log.debug("Create Ajax request for available forms");
        return formService.findAllUnassignedFormsByCurrentSchool();
    }

    /**
     * Request to get available forms by search options
     *
     * @param formSearchCriteria Search options
     * @return list of formDTOs
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/forms-wp", method = RequestMethod.POST)
    public @ResponseBody
    List<FormDTO> getAvailableFormsBySearchCriteria(@RequestBody FormSearchCriteria formSearchCriteria) {
        log.debug("Create Ajax request to search available forms by search criteria");
        Validate.notNull(formSearchCriteria.getLessonPosition(), "Field 'lessonPosition' on formSearchCriteria can not be null.");
        Validate.notNull(formSearchCriteria.getDate(), "Field 'Date' on  formSearchCriteria can not be null.");
        if (formSearchCriteria.getFormId() != null) {
            return formService.findAllAvailablePlusOneById(formSearchCriteria);
        }
        return formService.findAvailableFormsByCurrentSchoolAndSearchCriteria(formSearchCriteria);
    }
}
