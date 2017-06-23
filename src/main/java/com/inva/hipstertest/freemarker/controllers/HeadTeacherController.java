package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.service.FormService;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.FormDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import com.inva.hipstertest.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
public class HeadTeacherController {

    private final Logger log = LoggerFactory.getLogger(HeadTeacherController.class);
    private final TeacherService teacherService;
    private final SchoolService schoolService;
    private final FormService formService;
    private final UserService userService;
    private static final String ENTITY_NAME = "teacher";

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
    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt", method = RequestMethod.GET)
    public String teacherManagement(@ModelAttribute("model") ModelMap model) {
        log.debug("request to get teacher by current user");
        TeacherDTO currentTeacher = teacherService.findTeacherByCurrentUser();
        log.debug("request to get all teachers by School Id " + currentTeacher.getSchoolId());
        List<TeacherDTO> teachers = teacherService.getAllBySchoolId(currentTeacher.getSchoolId());
        model.addAttribute("currentTeacher", currentTeacher);
        model.addAttribute("teachers", teachers);
        return "teacher-mgmt/teacher-mgmt";
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-create", method = RequestMethod.GET)
    public ModelAndView teacherManagementCreate() {
        TeacherDTO teacherDTO = new TeacherDTO();
        return new ModelAndView("teacher-mgmt/teacher-mgmt-create", "teacherDTO", teacherDTO);
    }

    /**
     * Creates new teacher in school.
     *
     */
    @PostMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-create")
    @Timed
    public ModelAndView teacherManagementCreateNew(TeacherDTO teacherDTO, BindingResult bindingResult, String emailFail) throws URISyntaxException {
        log.debug("Freemarker request to save Teacher : {}", teacherDTO);
        log.debug(teacherDTO.getFirstName() + " " + teacherDTO.getLastName() + " " + teacherDTO.getEmail());
        TeacherDTO result = teacherService.saveTeacherWithUser(teacherDTO);
        emailFail = "Invalid e-mail";
        if(!result.getEnabled()){
            // handle email already in use
            return new ModelAndView("teacher-mgmt/teacher-mgmt-create", "emailFail", emailFail);
        }
        // handle creation success
        return new ModelAndView("redirect:/freemarker/teacher-mgmt/teacher-mgmt");
    }

    /**
     * Toggles teacher's "enabled" field.
     * @param id teacher to toggle
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt-toggle/{id}", method = RequestMethod.GET)
    public ModelAndView teacherManagementDisable(@ModelAttribute("model") ModelMap model, @PathVariable Long id){
        TeacherDTO currentTeacher = teacherService.findTeacherByCurrentUser();
        TeacherDTO teacherToToggle = teacherService.findOne(id);
        if (currentTeacher.getSchoolId().equals(teacherToToggle.getSchoolId())) {
            if(teacherToToggle.getEnabled()){
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
     * @param id - ID of teacher to be deleted
     *
     */
    @GetMapping("/freemarker/teacher-mgmt-delete/{id}")
    @Timed
    public ModelAndView teacherManagementDelete(@PathVariable Long id){
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
     * @param id - ID of teacher to Edit
     * @return teacherDTO
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-edit", method = RequestMethod.POST)
    public @ResponseBody TeacherDTO editRequest(@RequestBody Long id){
        log.debug("Create Ajax edit request");
        TeacherDTO teacherDTOToSend = teacherService.findOne(id);
        return teacherDTOToSend;
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-save", method = RequestMethod.POST)
    public @ResponseBody String saveRequest(@RequestBody @Valid TeacherDTO teacherDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "Error";
        }
        teacherService.saveTeacherAndUser(teacherDTO);
        return "Success";
    }

    /**
     * Request to get available forms to assign to Teacher
     * @return available forms
     */
    @RequestMapping(value = "freemarker/teacher-mgmt/teacher-mgmt-get-av-forms", method = RequestMethod.GET)
    public @ResponseBody List<FormDTO> getAvailableForms(){
        log.debug("Create Ajax request for available forms");
        List<FormDTO> forms = formService.findAllUnassignedFormsByCurrentSchool();
        return forms;
    }

}
