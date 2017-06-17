package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.FormService;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HeadTeacherController {

    private final Logger log = LoggerFactory.getLogger(HeadTeacherController.class);
    private final TeacherService teacherService;
    private final SchoolService schoolService;
    private final FormService formService;
    private final UserService userService;

    public HeadTeacherController(TeacherService teacherService, SchoolService schoolService,
                                 FormService formService, UserService userService){
        this.teacherService = teacherService;
        this.schoolService = schoolService;
        this.formService = formService;
        this.userService = userService;
    }

    /**
     * Saves the static list of teachers in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The view (FTL)
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt", method = RequestMethod.GET)
    public String teacherManagement(@ModelAttribute("model") ModelMap model) {
        log.debug("request to get teacher by current user");
        TeacherDTO currentTeacher = teacherService.findTeacherByCurrentUser();
        log.debug("request to get all teachers by School Id " + currentTeacher.getSchoolId());
        List<TeacherDTO> teachers = teacherService.getAllBySchoolId(currentTeacher.getSchoolId());
        model.addAttribute("currentTeacher", currentTeacher);
        model.addAttribute("teachers", teachers);
        return "teacher-mgmt/teacher-mgmt";
    }

    /**
     * Opens the page to create a new Teacher in School.
     *
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-create", method = RequestMethod.GET)
    public String teacherManagementCreate(@ModelAttribute("model") ModelMap model){
        return "teacher-mgmt/teacher-mgmt-create";
    }

    /**
     * Opens the page to create a new Teacher in School.
     *
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-details/{id}", method = RequestMethod.GET)
    public ModelAndView teacherManagementDetails(@ModelAttribute("model") ModelMap model, @PathVariable Long id){
        TeacherDTO teacher = teacherService.findOne(id);
        return new ModelAndView("teacher-mgmt/teacher-mgmt-details", "teacher", teacher);
    }

}
