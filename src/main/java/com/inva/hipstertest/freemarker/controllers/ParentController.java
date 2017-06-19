package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.dto.PupilDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ParentController {

    private final PupilService pupilService;
    private final ParentService parentService;

    public ParentController(PupilService pupilService, ParentService parentService) {
        this.pupilService = pupilService;
        this.parentService = parentService;
    }

    @RequestMapping(value = "freemarker/parent-home", method = RequestMethod.GET)
    public String parent(@ModelAttribute("model") ModelMap model) {
        Long parentId = parentService.findParentByCurrentUser().getId();
        List<PupilDTO> pupilList = pupilService.findAllByParentId(parentId);
        model.addAttribute("pupilList", pupilList);
        model.addAttribute("parentId", parentId);
        return "parent-home";
    }

}
