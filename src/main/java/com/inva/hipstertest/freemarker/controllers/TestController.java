package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.domain.Schedule;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.SchoolDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class TestController {

    private final SchoolService schoolService;
    private final TeacherService teacherService;
    private final PupilService pupilService;
    private final ParentService parentService;

    public TestController(SchoolService schoolService, TeacherService teacherService, PupilService pupilService, ParentService parentService) {
        this.schoolService = schoolService;
        this.teacherService = teacherService;
        this.pupilService = pupilService;
        this.parentService = parentService;
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
        if (schoolDTO.getName() != null && !schoolDTO.getName().isEmpty() &&
            schoolDTO.getEnabled() != null) {
            schoolService.save(schoolDTO);
            return "redirect:";
        } else {
            return "redirect:error"; //TODO: create error page
        }

    }

    /**
     * Get list of teachers.
     *
     * @param model
     * @param schoolId
     * @return The index view (FTL)
     */
    @RequestMapping(value = "freemarker/teachers/{schoolId}", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model, @PathVariable Long schoolId) {
        List<TeacherDTO> teachers = teacherService.getAllBySchoolId(schoolId);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("schoolId", schoolId);
        return "teachers";
    }

    /**
     * Get list of children of current parent.
     *
     * @param model
     * @return The index view (FTL)
     */
    @RequestMapping(value = "freemarker/parent", method = RequestMethod.GET)
    public String parent(@ModelAttribute("model") ModelMap model) {
//        Long parentId = parentService.findParentByCurrentUser().getId(); // edit, when logging by roles will be implemented
        Long parentId = (long)1;
        List<PupilDTO> pupilList = pupilService.findAllByParentId(parentId);
        model.addAttribute("pupilList", pupilList);
        model.addAttribute("parentId", parentId);
        return "parent";
    }

}
