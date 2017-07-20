package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class HeadTeacherController {

    private final Logger log = LoggerFactory.getLogger(HeadTeacherController.class);
    private final TeacherService teacherService;
    private final SchoolService schoolService;
    private static final String ENTITY_NAME = "teacher";

    public HeadTeacherController(TeacherService teacherService, SchoolService schoolService) {
        this.teacherService = teacherService;
        this.schoolService = schoolService;
    }

    /**
     * Saves the static list of teachers in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The view (FTL)
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt", method = RequestMethod.GET)
    public String teacherManagement(@ModelAttribute("model") ModelMap model) {
        log.debug("request to get teacher by current user");
        TeacherDTO currentUser = teacherService.findTeacherByCurrentUser();
        log.debug("request to get all teachers by School Id " + currentUser.getSchoolId());
        Boolean schoolEnabled = schoolService.getSchoolStatus(currentUser.getSchoolId());
        log.debug("request to get all teachers by School Id " + currentUser.getSchoolId());
        List<TeacherDTO> teachers = teacherService.getAllBySchoolId(currentUser.getSchoolId());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("schoolEnabled", schoolEnabled);
        model.addAttribute("teachers", teachers);
        if (!schoolEnabled) {
            model.addAttribute("currentUser", currentUser);
            return "schoolDisabledPage";
        }
        return "teacher-mgmt/teacher-mgmt";
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-create", method = RequestMethod.GET)
    public ModelAndView teacherManagementCreate(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacherDTO = new TeacherDTO();
        TeacherDTO currentUser = teacherService.findTeacherByCurrentUser();
            Boolean schoolEnabled=schoolService.getSchoolStatus(currentUser.getSchoolId());
               if (!schoolEnabled){
                       model.addAttribute("currentUser", currentUser);
                       return  new ModelAndView ("schoolDisabledPage");
                   }
        return new ModelAndView("teacher-mgmt/teacher-mgmt-create", "teacherDTO", teacherDTO);
    }

    /**
     * Creates new teacher in school.
     */
    @PostMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-create")
    @Timed
    public ModelAndView teacherManagementCreateNew(TeacherDTO teacherDTO, BindingResult bindingResult, String emailFail) throws URISyntaxException {
        log.debug("Freemarker request to save Teacher : {}", teacherDTO);
        log.debug(teacherDTO.getFirstName() + " " + teacherDTO.getLastName() + " " + teacherDTO.getEmail());
        TeacherDTO result = teacherService.saveTeacherWithUser(teacherDTO);
        emailFail = "Invalid e-mail";
        if (!result.getEnabled()) {
            // handle email already in use
            return new ModelAndView("teacher-mgmt/teacher-mgmt-create", "emailFail", emailFail);
        }
        // handle creation success
        return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
    }

    /**
     * Toggles teacher's "enabled" field.
     *
     * @param id teacher to toggle
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt-toggle/{id}", method = RequestMethod.GET)
    public ModelAndView teacherManagementDisable(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
        TeacherDTO currentTeacher = teacherService.findTeacherByCurrentUser();
        TeacherDTO teacherToToggle = teacherService.findOne(id);
        if (currentTeacher.getSchoolId().equals(teacherToToggle.getSchoolId())) {
            if (teacherToToggle.getEnabled()) {
                teacherToToggle.setEnabled(false);
            } else {
                teacherToToggle.setEnabled(true);
            }
            teacherService.save(teacherToToggle);
            return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
        } else {
            // illegal teacher id (does not belong to current school)
            return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
        }
    }

    /**
     * Deletes Teacher by ID
     *
     * @param id - ID of teacher to be deleted
     */
    @GetMapping("/freemarker/teacher-mgmt-delete/{id}")
    @Timed
    public ModelAndView teacherManagementDelete(@PathVariable Long id) {
        log.debug("Freemarker request to delete Teacher : {}", id);
        TeacherDTO currentTeacher = teacherService.findTeacherByCurrentUser();
        TeacherDTO teacherToDelete = teacherService.findOne(id);
        if (currentTeacher.getSchoolId().equals(teacherToDelete.getSchoolId())) {
            teacherService.delete(id);
            return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
        } else {
            return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
        }
    }

    /**
     * Request to receive complete teacherDTO for further editing
     *
     * @param id - ID of teacher to Edit
     * @return teacherDTO
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-edit", method = RequestMethod.POST)
    public @ResponseBody
    TeacherDTO editRequest(@RequestBody Long id) {
        log.debug("Create Ajax edit request");
        return teacherService.findOne(id);
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-save", method = RequestMethod.POST)
    public @ResponseBody
    String saveRequest(@RequestBody @Valid TeacherDTO teacherDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Error";
        }
        teacherService.saveTeacherAndUser(teacherDTO);
        return "Success";
    }

    /**
     * Scheduling control entry point
     *
     * @return scheduling control view
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt", method = RequestMethod.GET)
    public String scheduling(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        model.addAttribute("teacher", teacher);
        log.debug("request to get school status by current user");
        Boolean schoolEnabled = schoolService.getSchoolStatus(teacher.getSchoolId());
        if (schoolEnabled == false) {
            model.addAttribute("currentUser", teacher);
            return "schoolDisabledPage";
        }
        return "scheduling-control";
    }


    /**
     * Get all teachers from current school.
     *
     * @return list of teachers DTOs
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/teachers", method = RequestMethod.GET)
    public @ResponseBody List<TeacherDTO> getAllTeachersFromCurrentSchool(){
        log.debug("Create Ajax request for all teachers");
        return teacherService.findAllByCurrentSchool();
    }


}
