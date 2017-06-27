package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.LessonService;
import com.inva.hipstertest.service.dto.LessonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LessonController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(value = "freemarker/teacher-mgmt-lessons", method = RequestMethod.GET)
    public String lessonList(@ModelAttribute("model")ModelMap model) {
        List<LessonDTO> lessonList = lessonService.findAll();
        model.addAttribute("lessonList", lessonList);
        return "teacher-mgmt/teacher-mgmt-lessons";
    }

    @RequestMapping(value = "/freemarker/teacher-mgmt/teacher-mgmt-lessons", method = RequestMethod.GET)
    public ModelAndView teacherManagementCreate() {
        LessonDTO lessonDTO = new LessonDTO();
        return new ModelAndView("teacher-mgmt/teacher-mgmt-lessons", "lessonDTO", lessonDTO);
    }
}
