package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.domain.LessonType;
import com.inva.hipstertest.freemarker.searchcriteria.LessonsSearchCriteria;
import com.inva.hipstertest.service.LessonService;
import com.inva.hipstertest.service.LessonTypeService;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.LessonDTO;
import com.inva.hipstertest.service.dto.LessonTypeDTO;
import org.apache.commons.lang3.Validate;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LessonController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final LessonService lessonService;
    private final LessonTypeService lessonTypeService;
    private final TeacherService teacherService;
    private final SchoolService schoolService;

    public LessonController(LessonService lessonService, LessonTypeService lessonTypeService, TeacherService teacherService, SchoolService schoolService) {
        this.lessonService = lessonService;
        this.lessonTypeService = lessonTypeService;
        this.teacherService = teacherService;
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-lessons", method = RequestMethod.GET)
    public String data(@ModelAttribute("model") ModelMap model) {
        List<LessonDTO> lessonList;
        log.debug("request to get school status by current user");
               TeacherDTO currentUser = teacherService.findTeacherByCurrentUser();
              Boolean schoolEnabled=schoolService.getSchoolStatus(currentUser.getSchoolId());
               if (schoolEnabled==false){
                        model.addAttribute("currentUser", currentUser);
                       return "schoolDisabledPage";
                   }
        lessonList = lessonService.findAll();
        model.addAttribute("lessonList", lessonList);
        return "teacher-mgmt/teacher-mgmt-lessons";
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-add-lesson", method = RequestMethod.GET)
    public String index() {
        return "teacher-mgmt/teacher-mgmt-add-lesson";
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/addLesson", method = RequestMethod.GET)
    public ModelAndView adminNewSchool() {
        LessonDTO lessonDTO = new LessonDTO();
        return new ModelAndView("admin/admin-home-add-lesson", "lessonDTO", lessonDTO);
    }

    @PostMapping(value = "/freemarker/teacher-mgmt/addLesson")
    @Timed
    public String addLesson(@ModelAttribute("lessonDTO") LessonDTO lessonDTO) {
        if (lessonDTO.getName() != null && !lessonDTO.getName().isEmpty() &&
            lessonDTO.getEnabled() != null) {
            lessonService.save(lessonDTO);
            return "redirect:/freemarker/teacher-mgmt/teacher-mgmt-lessons/";
        } else {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "freemarker/teacher-mgmt/schedule-mgmt/lessons", method = RequestMethod.POST)
    public @ResponseBody
    List<LessonDTO> getLessonsBySearchCriteria(@RequestBody LessonsSearchCriteria lessonSearchCriteria){
        log.debug("Create Ajax request to search lessons by search criteria");
        Validate.notNull(lessonSearchCriteria.getId(), "Field 'id' on search criteria can not be empty.");
        Validate.notNull(lessonSearchCriteria.getLessonFilterType(), "Field 'Lessons for' on search criteria can not be empty.");
        return lessonService.getLessonsBySearchCriteria(lessonSearchCriteria);
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/schedule-mgmt/lesson-type", method = RequestMethod.GET)
    public @ResponseBody List<LessonTypeDTO> getLessonType(){
        log.debug("Create Ajax request for lesson type");
        return lessonTypeService.findAll();
    }

}
