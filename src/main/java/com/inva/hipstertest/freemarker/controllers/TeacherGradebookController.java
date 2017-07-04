package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.*;
import com.inva.hipstertest.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
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
    private final CourseService courseService;

    public TeacherGradebookController(TeacherService teacherService, ScheduleService scheduleService, PupilService pupilService, AttendancesService attendancesService, CourseService courseService) {
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.pupilService = pupilService;
        this.attendancesService = attendancesService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook", method = RequestMethod.GET)
    public ModelAndView home(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<CourseDTO> formsAndLessons = courseService.findByTeacherId(teacher.getId());
        if(formsAndLessons.isEmpty()) {
            return new ModelAndView("redirect:/freemarker/error");
        }
        Collections.sort(formsAndLessons, (o1, o2) -> o1.getFormName().compareTo(o2.getFormName()));
        CourseDTO formAndLesson = formsAndLessons.get(0);
        return new ModelAndView("redirect:/freemarker/teacher-gradebook/" + formAndLesson.getFormId() + "/" + formAndLesson.getLessonId());
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/{formId}/{lessonId}", method = RequestMethod.GET)
    public String gradebook(@ModelAttribute("model") ModelMap model, @PageableDefault(value = 10) Pageable pageable, @PathVariable Long formId, @PathVariable Long lessonId) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<CourseDTO> formsAndLessons = courseService.findByTeacherId(teacher.getId());
        if(formsAndLessons.isEmpty()) {
            return "redirect:/freemarker/error";
        }
        CourseDTO formAndLesson = null;

        for(CourseDTO item : formsAndLessons) {
            if(item.getFormId() == formId && item.getLessonId() == lessonId) {
                formAndLesson = item;
            }
        }

        Page<ScheduleDTO> page = scheduleService.findSchedulesByTeacherIdFormIdSubjectIdMaxDate(pageable, teacher.getId(), formId, lessonId, ZonedDateTime.now());
        List<PupilDTO> pupilDTOs = pupilService.findAllByFormId(formId);
        Comparator<PupilDTO> comparatorLastNameFirstName = Comparator.comparing(PupilDTO::getLastName).thenComparing(PupilDTO::getFirstName);
        List<AttendancesDTO> attendancesDTOs = attendancesService.findAllWherePupilIdInAndScheduleIdIn(teacher.getId(), formId, lessonId);

        Collections.sort(formsAndLessons, (o1, o2) -> o1.getFormName().compareTo(o2.getFormName()));
        Collections.sort(pupilDTOs, comparatorLastNameFirstName);

        model.addAttribute("teacher", teacher.getFirstName() + " " + teacher.getLastName());
        model.addAttribute("formsAndLessons", formsAndLessons);
        model.addAttribute("formAndLesson", formAndLesson);
        model.addAttribute("pupils", pupilDTOs);
        model.addAttribute("attendances", attendancesDTOs);
        model.addAttribute("schedules", page.getContent());
        model.addAttribute("sizes", pageable.getPageSize());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("longs", pages(pageable.getPageSize(), teacher.getId(), formId, lessonId, ZonedDateTime.now()));
//        model.addAttribute("formId", formId);
//        model.addAttribute("lessonId", lessonId);

        return "teacher-gradebook";
    }

    public long pages(int size, Long teacherId, Long formId, Long lessonId, ZonedDateTime today) {
        long all = scheduleService.countSchedulesForGradeBook(teacherId, formId, lessonId, today);
        long realPage = all/size;
        if(all % size == 0){
            return realPage;
        }
        return realPage + 1;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/update", method = RequestMethod.POST)
    public @ResponseBody
    AttendancesDTO updateSchedule(@RequestBody AttendancesDTO attendancesDTO) {
        log.debug("REST request to create/update Schedule : {}", attendancesDTO);
        AttendancesDTO newAttendancesDTO = attendancesService.save(attendancesDTO);
        return newAttendancesDTO;
    }

}
