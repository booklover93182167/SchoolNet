package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.domain.Form;
import com.inva.hipstertest.repository.PupilRepository;
import com.inva.hipstertest.service.*;
import com.inva.hipstertest.service.dto.*;
import com.inva.hipstertest.service.mapper.FormMapper;
import com.inva.hipstertest.service.mapper.PupilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.*;

@Controller
public class TeacherGradebookController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeacherService teacherService;
    private final ScheduleService scheduleService;
    private final PupilService pupilService;
    private final AttendancesService attendancesService;
    private final SchoolService schoolService;
    private final FormService formService;
    private final FormMapper formMapper;
    private final PupilRepository pupilRepository;
    private final PupilMapper pupilMapper;
    private final LessonService lessonService;
    private final AttendancesLogService attendancesLogService;
    private final ParentService parentService;

    public TeacherGradebookController(TeacherService teacherService, ScheduleService scheduleService, PupilService pupilService,
                                      AttendancesService attendancesService, SchoolService schoolService, FormService formService,
                                      FormMapper formMapper, PupilRepository pupilRepository, PupilMapper pupilMapper, ParentService parentService,
                                      LessonService lessonService, AttendancesLogService attendancesLogService) {
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.pupilService = pupilService;
        this.attendancesService = attendancesService;
        this.schoolService = schoolService;
        this.formService = formService;
        this.formMapper = formMapper;
        this.pupilRepository = pupilRepository;
        this.pupilMapper = pupilMapper;
        this.lessonService = lessonService;
        this.attendancesLogService = attendancesLogService;
        this.parentService = parentService;
    }

    @RequestMapping(value = {"/freemarker/teacher-gradebook", "/freemarker/teacher-gradebook/{formId}/{lessonId}"}, method = RequestMethod.GET)
    public String gradebook(@ModelAttribute("model") ModelMap model, @PageableDefault(value = 10) Pageable pageable, @PathVariable Optional<Long> formId, @PathVariable Optional<Long> lessonId) {
        TeacherDTO teacher = teacherService.findTeacherByCurrentUser();
        model.addAttribute("teacher", teacher);

        log.debug("request to get school status by current user");
        Boolean schoolEnabled = schoolService.getSchoolStatus(teacher.getSchoolId());
        if (schoolEnabled == false) {
            model.addAttribute("currentUser", teacher);
            return "schoolDisabledPage";
        }

        List<FormDTO> forms = formService.findAllFormsByCurrentSchool();
        model.addAttribute("forms", forms);

        if (!formId.isPresent() || !lessonId.isPresent()) {
            return "teacher-gradebook";
        }

        model.addAttribute("form", formService.findOne(formId.get()));
        model.addAttribute("lesson", lessonService.findOne(lessonId.get()));

        Page<ScheduleDTO> page = scheduleService.findAllByFormIdLessonIdMaxDate(pageable, formId.get(), lessonId.get(), ZonedDateTime.now());

        if (!page.hasContent()) {
            model.addAttribute("error", 2);
            return "teacher-gradebook";
        }

        List<PupilDTO> pupils = pupilService.findAllByFormId(formId.get());
        List<AttendancesDTO> attendances = attendancesService.findAllByFormIdAndLessonId(formId.get(), lessonId.get());
        Comparator<PupilDTO> comparatorLastNameFirstName = Comparator.comparing(PupilDTO::getLastName).thenComparing(PupilDTO::getFirstName);

        Collections.sort(pupils, comparatorLastNameFirstName);

        model.addAttribute("minDateForEdit", ZonedDateTime.now().minusDays(14));
        model.addAttribute("pupils", pupils);
        model.addAttribute("attendances", attendances);
        model.addAttribute("schedules", page.getContent());
        model.addAttribute("sizes", pageable.getPageSize());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("longs", pages(pageable.getPageSize(), formId.get(), lessonId.get(), ZonedDateTime.now()));

        return "teacher-gradebook";
    }

    public long pages(int size, Long formId, Long lessonId, ZonedDateTime maxDate) {
        long all = scheduleService.countAllByFormIdLessonIdMaxDate(formId, lessonId, maxDate);
        long realPage = all / size;
        if (all % size == 0) {
            return realPage;
        }
        return realPage + 1;
    }

    @RequestMapping(value = "freemarker/teacher-gradebook/lessons", method = RequestMethod.POST)
    public @ResponseBody
    List<LessonDTO> formLessons(@RequestBody Long formId){
        log.debug("Create ajax request to get lessons for form " + formId);
        List<LessonDTO> lessonDTOs = lessonService.getDistinctLessonsForForm(formId);
        Collections.sort(lessonDTOs, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return lessonDTOs;
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/update", method = RequestMethod.POST)
    public @ResponseBody
    AttendancesDTO updateSchedule(@RequestBody AttendancesDTO attendances) {
        log.debug("REST request to create/update Schedule : {}", attendances);
        if ((attendances.getGrade() == null) || (attendances.getGrade() >= 0 && attendances.getGrade() <= 12)) {
            return attendancesService.save(attendances);
        }
        // invalid grade exception
        throw new RuntimeException();
    }

    @RequestMapping(value = "/freemarker/teacher-gradebook/log-attendance", method = RequestMethod.POST)
    public @ResponseBody
    AttendancesLogDTO logNewAttendance(@RequestBody AttendancesLogDTO attendancesLog) {
        log.debug("REST request to add attendance to log : {}", attendancesLog);
        AttendancesLogDTO newAttendancesLog = attendancesLogService.save(attendancesLog);
        return newAttendancesLog;
    }



}
