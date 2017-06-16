package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.service.*;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PupilController {
    private final PupilService pupilService;
    private final ScheduleService scheduleService;
    private final FormService formService;

    public PupilController(PupilService pupilService, ScheduleService scheduleService, FormService formService) {
        this.pupilService = pupilService;
        this.scheduleService = scheduleService;
        this.formService = formService;
    }

    /**
     * Saves the static list of users in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The index view (FTL)
     */
    @RequestMapping(value = "freemarker/pupil-home", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        PupilDTO pupil = pupilService.findPupilByCurrentUser();
        List<ScheduleDTO> schedule = scheduleService.findAllByFormId(pupil.getFormId());
        model.addAttribute("currentPupil", pupil);
        model.addAttribute("mySchedule", schedule);
        return "pupil-home";
    }
}
