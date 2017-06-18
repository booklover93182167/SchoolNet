package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.FormService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.ScheduleService;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.ScheduleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "freemarker/")
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
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "pupil-home", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        PupilDTO pupil = pupilService.findPupilByCurrentUser();
        List<ScheduleDTO> schedule = scheduleService.findAllByFormId(pupil.getFormId());
        model.addAttribute("currentPupil", pupil);
        model.addAttribute("mySchedule", schedule);
        return "pupil-home";
    }

    /**
     *
     * @param formId
     * @return
     */
    @RequestMapping("pupil-home/myShedule/{formId}")
    public @ResponseBody List<ScheduleDTO> getListSchedulesByDate(@PathVariable Long formId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        List<ScheduleDTO> scheduleDTO = scheduleService.findByFormIdAndDate(zonedDateTime, formId);
        return scheduleDTO;
    }

}
