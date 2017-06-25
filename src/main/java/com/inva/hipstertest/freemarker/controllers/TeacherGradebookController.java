package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.AttendancesService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.AttendancesDTO;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class TeacherGradebookController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeacherService teacherService;
    private final ScheduleService scheduleService;
    private final PupilService pupilService;
    private final AttendancesService attendancesService;

    public TeacherGradebookController(TeacherService teacherService, ScheduleService scheduleService, PupilService pupilService, AttendancesService attendancesService) {
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.pupilService = pupilService;
        this.attendancesService = attendancesService;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook", method = RequestMethod.GET)
    public ModelAndView home(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<ScheduleDTO> formsAndLessons = scheduleService.findAllByTeacherIdGroupByFormIdAndLessonId(teacher.getId());
        Collections.sort(formsAndLessons, (o1, o2) -> o1.getFormName().compareTo(o2.getFormName()));
        ScheduleDTO formAndLesson = formsAndLessons.get(0);
        return new ModelAndView("redirect:/freemarker/teacher-gradebook/" + formAndLesson.getFormId() + "/" + formAndLesson.getLessonId());
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/{formId}/{lessonId}", method = RequestMethod.GET)
    public String gradebook(@ModelAttribute("model") ModelMap model, @PathVariable Long formId, @PathVariable Long lessonId) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<ScheduleDTO> formsAndLessons = scheduleService.findAllByTeacherIdGroupByFormIdAndLessonId(teacher.getId());
//        ScheduleDTO formAndLesson = formsAndLessons.get(0);
        List<ScheduleDTO> schedulesDTOs = scheduleService.findAllByTeacherIdAndFormIdAndLessonIdOrderByDate(teacher.getId(), formId, lessonId);
        List<PupilDTO> pupilDTOs = pupilService.findAllByFormId(formId);
        Comparator<PupilDTO> comparatorLastNameFirstName = Comparator.comparing(PupilDTO::getLastName).thenComparing(PupilDTO::getFirstName);
        List<AttendancesDTO> attendancesDTOs = attendancesService.findAllWherePupilIdInAndScheduleIdIn(teacher.getId(), formId, lessonId);

        Collections.sort(formsAndLessons, (o1, o2) -> o1.getFormName().compareTo(o2.getFormName()));
        Collections.sort(pupilDTOs, comparatorLastNameFirstName);

//        model.addAttribute("formAndLesson", formAndLesson);
        model.addAttribute("teacher", teacher.getFirstName() + " " + teacher.getLastName());
        model.addAttribute("formsAndLessons", formsAndLessons);
        model.addAttribute("schedules", schedulesDTOs);
        model.addAttribute("attendances", attendancesDTOs);
        model.addAttribute("formId", formId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("pupils", pupilDTOs);

        return "teacher-gradebook";
    }

}
