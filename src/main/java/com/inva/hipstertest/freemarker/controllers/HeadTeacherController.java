package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.freemarker.searchcriteria.ClassroomSearchCriteria;
import com.inva.hipstertest.freemarker.searchcriteria.FormSearchCriteria;
import com.inva.hipstertest.freemarker.searchcriteria.ScheduleSearchCriteria;
import com.inva.hipstertest.service.*;
import com.inva.hipstertest.service.dto.*;
import org.apache.commons.lang3.Validate;
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
    private final ScheduleService scheduleService;
    private final SchoolService schoolService;
    private final ClassroomService classroomService;
    private final FormService formService;
    private final UserService userService;
    private static final String ENTITY_NAME = "teacher";

    public HeadTeacherController(TeacherService teacherService, ScheduleService scheduleService, SchoolService schoolService,
                                 ClassroomService classroomService, FormService formService, UserService userService){
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.schoolService = schoolService;
        this.classroomService = classroomService;
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

    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt", method = RequestMethod.GET)
    public String scheduling(@ModelAttribute("model") ModelMap model) {
        return "scheduling-control";
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/forms", method = RequestMethod.GET)
    public @ResponseBody List<FormDTO> getAllFormsFromCurrentSchool(){
        log.debug("Create Ajax request for all forms");
        List<FormDTO> forms = formService.findAllFormsByCurrentSchool();
        return forms;
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/teachers", method = RequestMethod.GET)
    public @ResponseBody List<TeacherDTO> getAllTeachersFromCurrentSchool(){
        log.debug("Create Ajax request for all teachers");
        List<TeacherDTO> teachers = teacherService.findAllByCurrentSchool();
        return teachers;
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/classrooms", method = RequestMethod.GET)
    public @ResponseBody List<ClassroomDTO> getAllClassroomsFromCurrentSchool(){
        log.debug("Create Ajax request for all classrooms");
        List<ClassroomDTO> classrooms = classroomService.findAllByCurrentSchool();
        return classrooms;
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/classrooms-wp", method = RequestMethod.POST)
    public @ResponseBody List<ClassroomDTO> getAvailableClassroomBySearchCriteria(@RequestBody ClassroomSearchCriteria classroomSearchCriteria){
        log.debug("Create Ajax request to search available forms by search criteria");
        Validate.notNull(classroomSearchCriteria.getLessonPosition(), "Field 'lessonPosition' on classroomSearchCriteria can not be null.");
        Validate.notNull(classroomSearchCriteria.getDate(), "Field 'Date' on  classroomSearchCriteria can not be null.");
        return classroomService.findAvailableClassroomsByCurrentSchoolAndSearchCriteria(classroomSearchCriteria);
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/schedule", method = RequestMethod.POST)
    public @ResponseBody List<ScheduleDTO> getScheduleBySearchCriteria(@RequestBody ScheduleSearchCriteria scheduleSearchCriteria){
        log.debug("Create Ajax request to search schedule by search criteria");
        Validate.notNull(scheduleSearchCriteria.getId(), "Field 'id' on scheduleSearchCriteria can not be null.");
        Validate.notNull(scheduleSearchCriteria.getScheduleFilterType(), "Field 'Schedule type' on scheduleSearchCriteria can not be null.");
        Validate.notNull(scheduleSearchCriteria.getDate(), "Field 'Date' on scheduleSearchCriteria can not be null.");
        return scheduleService.getScheduleBySearchCriteria(scheduleSearchCriteria);
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/forms-wp", method = RequestMethod.POST)
    public @ResponseBody List<FormDTO> getAvailableFormsBySearchCriteria(@RequestBody FormSearchCriteria formSearchCriteria){
        log.debug("Create Ajax request to search available forms by search criteria");
        Validate.notNull(formSearchCriteria.getLessonPosition(), "Field 'lessonPosition' on formSearchCreteria can not be null.");
        Validate.notNull(formSearchCriteria.getDate(), "Field 'Date' on  formSearchCreteria can not be null.");
        return formService.findAvailableFormsByCurrentSchoolAndSearchCriteria(formSearchCriteria);
    }

    /**
     * Get schedule by id.
     *
     * @param scheduleId schedule id
     * @return the ScheduleDTO.
     */
    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/schedule/{scheduleId}", method = RequestMethod.GET)
    public @ResponseBody ScheduleDTO getSchedulesById(@PathVariable("scheduleId") Long scheduleId) {
        log.debug("Request to get schedule by id : {}", scheduleId);
        ScheduleDTO scheduleDTO = scheduleService.findOne(scheduleId);
        return scheduleDTO;
    }


//
//    /**
//     * POST  /schedules : Create a new schedule.
//     *
//     * @param scheduleDTO the scheduleDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new scheduleDTO, or with status 400 (Bad Request) if the schedule has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/freemarker/teacher-mgmt/schedule-mgmt/schedule-create")
//    @Timed
//    public ScheduleDTO createNewSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) throws URISyntaxException {
//        log.debug("REST request to save Schedule : {}", scheduleDTO);
//        if (scheduleDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new schedule cannot already have an ID")).body(null);
//        }
//        ScheduleDTO result = scheduleService.save(scheduleDTO);
//        return ResponseEntity.created(new URI("/api/schedules/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
}
