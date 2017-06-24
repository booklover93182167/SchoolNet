package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping(value = "freemarker/")
public class TeacherGradebookController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeacherService teacherService;
    private final ScheduleService scheduleService;

    public TeacherGradebookController(TeacherService teacherService, ScheduleService scheduleService) {
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "teacher-gradebook", method = RequestMethod.GET)
    public String gradebook(@ModelAttribute("model") ModelMap model) {
        Long teacherId = teacherService.findTeacherByCurrentUser().getId();
        List<ScheduleDTO> formsAndLessonsDTOs = scheduleService.findAllByTeacherIdGroupByFormIdAndLessonId(teacherId);
        Collections.sort(formsAndLessonsDTOs, (o1, o2) -> o1.getFormName().compareTo(o2.getFormName()));

        model.addAttribute("teacherId", teacherId);
        model.addAttribute("formsAndLessons", formsAndLessonsDTOs);
        return "teacher-gradebook";
    }

}
