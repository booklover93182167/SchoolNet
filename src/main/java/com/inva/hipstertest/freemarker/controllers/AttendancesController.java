package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.AttendancesLogService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.AttendancesLogDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AttendancesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeacherService teacherService;
    private final AttendancesLogService attendancesLogService;

    AttendancesController(TeacherService teacherService, AttendancesLogService attendancesLogService) {
        this.teacherService = teacherService;
        this.attendancesLogService = attendancesLogService;
    }

    @RequestMapping(value = "freemarker/attendances-log", method = RequestMethod.GET)
    public String attendanceslog(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();

        List<AttendancesLogDTO> log = attendancesLogService.findAll();

        model.addAttribute("teacher", teacher);
        model.addAttribute("log", log);

        return "attendances-log";
    }

}
