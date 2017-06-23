package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.AttendancesService;
import com.inva.hipstertest.service.LessonService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.AttendancesDTO;
import com.inva.hipstertest.service.dto.LessonDTO;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "freemarker/")
public class PupilController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PupilService pupilService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final AttendancesService attendancesService;

    public PupilController(PupilService pupilService, ScheduleService scheduleService,
                           LessonService lessonService,
                           AttendancesService attendancesService) {
        this.pupilService = pupilService;
        this.scheduleService = scheduleService;
        this.lessonService = lessonService;
        this.attendancesService = attendancesService;
    }

    /**
     * Get name of Current pupil.
     *
     * @param model
     * @return hme page view (FTL).
     */
    @RequestMapping(value = "pupil-home", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        log.debug("Request to get current pupil");
        PupilDTO pupil = pupilService.findPupilByCurrentUser();
        model.addAttribute("pupilFirstName",pupil.getFirstName());
        model.addAttribute("pupilLastName",pupil.getLastName());
        return "pupil-home";
    }

    /**
     * Get schedules by date.
     *
     * @param date requested date
     * @return the list of schedules.
     */
    @RequestMapping("pupil-home/mySchedule/{date}")
    public @ResponseBody
    List<ScheduleDTO> getListSchedulesByDate(@PathVariable String date) {
        log.debug("Request to get schedule for current pupil by date : {}", date);
        List<ScheduleDTO> scheduleDTO = scheduleService.findAllByFormIdAndDate(date);
        return scheduleDTO;
    }

    /**
     * Get attendances by date.
     *
     * @param date requested date
     * @return te list of attendances.
     */
    @RequestMapping("pupil-home/myAttendances/{date}")
    public @ResponseBody
    List<AttendancesDTO> getListAttendancesByDate(@PathVariable String date) {
        log.debug("Request to get attendance for current pupil by date : {}", date);
        List<AttendancesDTO> attendancesDTOs = attendancesService.findAllMembersByPupilIdAndDateBetween(date);
        return attendancesDTOs;
    }

    /**
     * Get list of lessons for select.
     *
     * @param model
     * @return The attendances view (FTL)
     */
    @RequestMapping(value = "pupil/attendances", method = RequestMethod.GET)
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
    @RequestMapping(value = "pupil/att", method = RequestMethod.POST)
    public @ResponseBody List<AttendancesDTO> requestSome(@RequestBody LessonDTO lessonDTO){
        log.debug("Create Ajax request for attendance by id lesson");
        PupilDTO pupilDTO = pupilService.findPupilByCurrentUser();
        List<AttendancesDTO> attendancesDTO =
            attendancesService.findAllByPupilAndLessonId(pupilDTO.getId(),lessonDTO.getId());
        Collections.sort(attendancesDTO, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return attendancesDTO;
    }
}
