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

    public TeacherGradebookController(TeacherService teacherService, ScheduleService scheduleService,
                                      PupilService pupilService, AttendancesService attendancesService,
                                      CourseService courseService) {
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.pupilService = pupilService;
        this.attendancesService = attendancesService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook", method = RequestMethod.GET)
    public String home(@ModelAttribute("model") ModelMap model) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<CourseDTO> courses = courseService.findAllByTeacherId(teacher.getId());

        model.addAttribute("teacher", teacher.getFirstName() + " " + teacher.getLastName());

        if(courses.isEmpty()) {
            model.addAttribute("error", 1);
            return "teacher-gradebook";
        }
        return "redirect:/freemarker/teacher-gradebook/" + courses.get(0).getId();
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/{courseId}", method = RequestMethod.GET)
    public String gradebook(@ModelAttribute("model") ModelMap model, @PageableDefault(value = 10) Pageable pageable, @PathVariable Long courseId) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        List<CourseDTO> courses = courseService.findAllByTeacherId(teacher.getId());

        model.addAttribute("teacher", teacher.getFirstName() + " " + teacher.getLastName());

        if(courses.isEmpty()) {
            model.addAttribute("error", 1);
            return "teacher-gradebook";
        }

        model.addAttribute("courses", courses);
        CourseDTO course = courseService.findOne(courseId);

        if(course == null) {
            model.addAttribute("error", 2);
            return "teacher-gradebook";
        }

        model.addAttribute("course", course);

        if(!teacher.getId().equals(course.getTeacherId())) {
            model.addAttribute("error", 3);
            return "teacher-gradebook";
        }

        Page<ScheduleDTO> page = scheduleService.findAllByCourseIdAndMaxDate(pageable, course.getId(), ZonedDateTime.now());
        List<PupilDTO> pupils = pupilService.findAllByFormId(course.getFormId());
        List<AttendancesDTO> attendances = attendancesService.findAllByCourseId(course.getId());
        Comparator<PupilDTO> comparatorLastNameFirstName = Comparator.comparing(PupilDTO::getLastName).thenComparing(PupilDTO::getFirstName);
        Collections.sort(pupils, comparatorLastNameFirstName);

        model.addAttribute("pupils", pupils);
        model.addAttribute("attendances", attendances);
        model.addAttribute("schedules", page.getContent());
        model.addAttribute("sizes", pageable.getPageSize());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("longs", pages(pageable.getPageSize(), course.getId(), ZonedDateTime.now()));

        return "teacher-gradebook";
    }

    public long pages(int size, Long courseId, ZonedDateTime maxDate) {
        long all = scheduleService.countAllByCourseIdAndMaxDate(courseId, maxDate);
        long realPage = all/size;
        if(all % size == 0){
            return realPage;
        }
        return realPage + 1;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/update", method = RequestMethod.POST)
    public @ResponseBody
    AttendancesDTO updateSchedule(@RequestBody AttendancesDTO attendances) {
        log.debug("REST request to create/update Schedule : {}", attendances);
//        if ((attendances.getGrade() >= 0 && attendances.getGrade() <= 12) || attendances.getGrade() == null) {
            AttendancesDTO newAttendances = attendancesService.save(attendances);
            return newAttendances;
//        }
//        return attendances;
    }

}
