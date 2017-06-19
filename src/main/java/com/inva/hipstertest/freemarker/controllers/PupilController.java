package com.inva.hipstertest.freemarker.controllers;


import com.inva.hipstertest.service.AttendancesService;
import com.inva.hipstertest.service.LessonService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.dto.AttendancesDTO;
import com.inva.hipstertest.service.dto.LessonDTO;
import com.inva.hipstertest.service.dto.PupilDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PupilController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PupilService pupilService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private AttendancesService attendancesService;

    /**
     * Get list of lessons for select.
     *
     * @param model
     * @return The attendances view (FTL)
     */
    @RequestMapping(value = "freemarker/pupil/attendances", method = RequestMethod.GET)
    public String getCurrentPupilAttendances(Model model){
        log.debug("Request to get Attendances for current pupil");
        PupilDTO pupilDTO = pupilService.findPupilByCurrentUser();
        model.addAttribute("lessons",lessonService.getDistinctLessonsForForm(pupilDTO.getFormId()));
        model.addAttribute("pupilFirstName",pupilDTO.getFirstName());
        model.addAttribute("pupilLastName",pupilDTO.getLastName());
        return "attendances";
    }

    /**
     * Get list of lessons for select.
     *
     * @param lessonDTO with set id.
     * @return The List<AttendancesDTO> with attendance by choose id.lessons.
     */
    @RequestMapping(value = "freemarker/pupil/att", method = RequestMethod.POST)
    public @ResponseBody List<AttendancesDTO> requestSome(@RequestBody LessonDTO lessonDTO){
        log.debug("Create Ajax request for attendance by id lesson");
        PupilDTO pupilDTO = pupilService.findPupilByCurrentUser();
        List<AttendancesDTO> attendancesDTO =
            attendancesService.findAllByPupilAndLessonId(pupilDTO.getId(),lessonDTO.getId());
        Collections.sort(attendancesDTO, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return attendancesDTO;
    }




}
