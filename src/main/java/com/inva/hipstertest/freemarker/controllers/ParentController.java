package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ParentController {

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
        model.addAttribute("parentId", parentId);
        for ( PupilDTO pupil : pupilList ) {
            List<ScheduleDTO> schedule = scheduleService.findAllByFormId(pupil.getFormId());
            model.addAttribute("schedule" + pupil.getId(), schedule);
        }
        return "parent-home";
    }

}
