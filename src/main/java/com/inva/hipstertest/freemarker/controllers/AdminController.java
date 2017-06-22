package com.inva.hipstertest.freemarker.controllers;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.UserAddonService;
import com.inva.hipstertest.service.dto.SchoolDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(HeadTeacherController.class);
    private final SchoolService schoolService;
    private final TeacherService teacherService;


    private final UserAddonService userAddonService;


    public AdminController(SchoolService schoolService, TeacherService teacherService, UserAddonService userAddonService) {
        this.schoolService = schoolService;
        this.teacherService = teacherService;
        this.userAddonService = userAddonService;
    }

    @RequestMapping(value = "freemarker/admin-home", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        UserAddonDTO user = userAddonService.findByCurrentUser();
        List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
        schoolList = schoolService.findAll();

        model.addAttribute("schoolList", schoolList);
        model.addAttribute("currentUser", user);

        return "admin/admin-home";
    }

    /**
     * Add a new School
     *
     * @param schoolDTO
     * @return Redirect back to same /freemarkertest page to display school list, if successful
     */
    @RequestMapping(value = "freemarker/admin-home/add", method = RequestMethod.POST)
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
    @RequestMapping(value = "freemarker/teachersInSchool/{schoolId}", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model, @PathVariable Long schoolId) {
        List<TeacherDTO> teachers = teacherService.getAllBySchoolId(schoolId);
        String schoolName = schoolService.findOne(schoolId).getName();
        System.out.println(schoolName);
        model.addAttribute("teachersList", teachers);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("schoolName", schoolName);
        return "admin/teachersInSchool";
    }


    @RequestMapping(value = "/freemarker/admin-home/newSchool", method = RequestMethod.GET)
    public ModelAndView adminNewSchool() {
        SchoolDTO schoolDTO = new SchoolDTO();
        return new ModelAndView("admin/newSchool", "schoolDTO", schoolDTO);
    }

    /**
     * Creates new teacher in school.
     *
     */
    @PostMapping(value = "/freemarker/admin-home/newSchool")
    @Timed
    public ModelAndView adminNewSchool(SchoolDTO schoolDTO, BindingResult bindingResult, String nameFail) throws URISyntaxException {
        log.debug("Freemarker request to create school : {}", schoolDTO);
        if (bindingResult.hasErrors()) {
            log.debug("school binding has errors");
            return new ModelAndView("admin/newSchool");
        }

        SchoolDTO result = schoolService.save(schoolDTO);
        nameFail = "This name is already in use!";
        if(!result.getEnabled()){
            // handle email already in use
            return new ModelAndView("admin/newSchool", "nameFail", nameFail);
        }
        // handle creation success
        return new ModelAndView("redirect:admin/admin-home");
    }

}

