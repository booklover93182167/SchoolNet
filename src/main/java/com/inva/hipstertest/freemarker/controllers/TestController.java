package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.domain.Schedule;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.dto.SchoolDTO;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class TestController {
    private final SchoolService schoolService;

    public TestController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    /**
     * Saves the static list of users in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The index view (FTL)
     */
    @RequestMapping(value = "freemarker/freemarkertest", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
        schoolList = schoolService.findAll();
        model.addAttribute("schoolList", schoolList);
        return "freemarkertest";
    }

    /**
     * Add a new School
     *
     * @param schoolDTO
     * @return Redirect back to same /freemarkertest page to display school list, if successful
     */
    @RequestMapping(value = "freemarker/freemarkertest/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("schoolDTO") SchoolDTO schoolDTO) {
        if(schoolDTO.getName() != null && !schoolDTO.getName().isEmpty() &&
            schoolDTO.getEnabled() != null) {
            schoolService.save(schoolDTO);
            return "redirect:";
        } else {
            return "redirect:error"; //TODO: create error page
        }

    }
}
