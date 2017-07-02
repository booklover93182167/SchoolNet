package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.SchoolNetApp;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.service.BaseServiceTest;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.dto.TeacherDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolNetApp.class)
@Transactional
public class TeacherServiceImplTest extends BaseServiceTest{

    @Autowired
    private TeacherService teacherService;

    private User user;
    private School school;

    @Before
    public void setUp() throws Exception {
        user = userProvider.persistUserDefault();
        school = schoolProvider.persistSchoolDefault();
    }

    @Test
    public void shouldSave() throws Exception {
        TeacherDTO teacherDTO = TeacherDTO.builder()
            .enabled(true)
            .userId(user.getId())
            .schoolId(school.getId())
            .build();

        TeacherDTO savedTeacherDTO = teacherService.save(teacherDTO);
        assertNotNull(savedTeacherDTO);
        assertEquals(teacherDTO.getFirstName(), savedTeacherDTO.getFirstName());
        assertEquals(teacherDTO.getSchoolId(), savedTeacherDTO.getSchoolId());
    }

    @Test
    public void saveTeacherAndUser() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findTeacherByCurrentUser() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void saveTeacherWithUser() throws Exception {
    }

    @Test
    public void saveHeadTeacherWithUser() throws Exception {
    }

    @Test
    public void findAllByCurrentSchool() throws Exception {
    }

    @Test
    public void getAllBySchoolId() throws Exception {
    }

    @Test
    public void makeHeadTeacher() throws Exception {
    }

    @Test
    public void unMakeHeadTeacher() throws Exception {
    }

}
