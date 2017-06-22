package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.freemarker.pojo.ScheduleSearchParams;
import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ParentController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ParentService parentService;
    private final PupilService pupilService;
    private final ScheduleService scheduleService;

    public ParentController(ParentService parentService, PupilService pupilService, ScheduleService scheduleService) {
        this.parentService = parentService;
        this.pupilService = pupilService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "freemarker/parent-home", method = RequestMethod.GET)
    public String parent(@ModelAttribute("model") ModelMap model) {
        Long parentId = parentService.findParentByCurrentUser().getId();
        List<PupilDTO> pupilList = pupilService.findAllByParentId(parentId);
        model.addAttribute("pupilList", pupilList);
//        model.addAttribute("parentId", parentId);
//        for ( PupilDTO pupil : pupilList ) {
//            List<ScheduleDTO> schedule = scheduleService.findAllByFormId(pupil.getFormId());
//            Collections.sort(schedule, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
//            model.addAttribute("schedule" + pupil.getId(), schedule);
//        }
        return "parent-home";
    }

    @RequestMapping(value = "freemarker/parent-home/schedule", method = RequestMethod.POST)
    public @ResponseBody
    List<ScheduleDTO> requestSome(@RequestBody ScheduleSearchParams scheduleSearchParams){
        log.debug("Create ajax request for pupil schedule by pupil id and date: " + scheduleSearchParams.getDate());
        List<ScheduleDTO> scheduleDTOs = scheduleService.findAllByFormId(scheduleSearchParams.getPupilFormId());
//        Collections.sort(scheduleDTOs, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        long currentDayOfWeek = scheduleSearchParams.getDate().getDayOfWeek().getValue() % 7;
        ZonedDateTime prevSunday = scheduleSearchParams.getDate().minusDays(currentDayOfWeek);
        ZonedDateTime nextSunday = scheduleSearchParams.getDate().plusDays(7 - currentDayOfWeek);

        List<ScheduleDTO> filteredScheduleDTOs = new LinkedList<ScheduleDTO>();
        for (ScheduleDTO schedule : scheduleDTOs) {
            if( schedule.getDate().compareTo(prevSunday) > 0 && schedule.getDate().compareTo(nextSunday) < 0  ) {
                filteredScheduleDTOs.add(schedule);
            }
        }

        return filteredScheduleDTOs;
    }
}
