package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.domain.Schedule;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.service.SchoolService;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.UserAddonService;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.SchoolDTO;
import com.inva.hipstertest.service.dto.TeacherDTO;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final SchoolService schoolService;

    private final UserAddonService userAddonService;


    public AdminController(SchoolService schoolService, UserAddonService userAddonService) {
        this.schoolService = schoolService;
        this.userAddonService = userAddonService;
    }

    @RequestMapping(value = "freemarker/admin-home", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        UserAddonDTO user = userAddonService.findByCurrentUser();
        List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
        schoolList = schoolService.findAll();
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("currentUser", user);
        return "admin-home";
    }

    /**
     * Add a new School
     *
     * @param schoolDTO
     * @return Redirect back to same /freemarkertest page to display school list, if successful
     */
    //@RequestMapping(value = "freemarker/freemarkertest/add", method = RequestMethod.POST)
   // public String add(@ModelAttribute("schoolDTO") SchoolDTO schoolDTO) {
   //    if (schoolDTO.getName() != null && !schoolDTO.getName().isEmpty() &&
     //       schoolDTO.getEnabled() != null) {
     //       schoolService.save(schoolDTO);
    //        return "redirect:";
    //    } else {
     ////       return "redirect:error"; //TODO: create error page
     //   }

   // }

    /**
     * Get list of teachers.
     *
     * @param model
     * @param schoolId
     * @return The index view (FTL)
     */
   // @RequestMapping(value = "freemarker/teachers/{schoolId}", method = RequestMethod.GET)
   // public String index(@ModelAttribute("model") ModelMap model, @PathVariable Long schoolId) {
   //     List<TeacherDTO> teachers = teacherService.getAllBySchoolId(schoolId);
   //    model.addAttribute("teachersList", teachers);
    //    model.addAttribute("schoolId", schoolId);
    //    return "teachers";
   // }

}
