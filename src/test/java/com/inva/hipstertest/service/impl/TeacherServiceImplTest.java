package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.SchoolNetApp;
import com.inva.hipstertest.data.provider.TeacherProvider;
import com.inva.hipstertest.data.provider.UserProvider;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.domain.Teacher;
import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.repository.TeacherRepository;
import com.inva.hipstertest.repository.UserRepository;
import com.inva.hipstertest.service.BaseServiceTest;
import com.inva.hipstertest.service.TeacherService;
import com.inva.hipstertest.service.TestContext;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.TeacherDTO;
import com.inva.hipstertest.service.mapper.TeacherMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolNetApp.class)
@Transactional
@ContextConfiguration(classes = TestContext.class)
public class TeacherServiceImplTest extends BaseServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    private TeacherService teacherService;
    private UserService userService;

    private User user;
    private School school;
    private Teacher teacher;

    @Before
    public void setUp() throws Exception {
        when(teacherRepository.findOneWithEagerRelationships(anyLong()))
            .thenReturn(TeacherProvider.getTeacher(10L));

        when(teacherMapper.teacherToTeacherDTO(any(Teacher.class)))
            .thenAnswer(invocationOnMock -> {
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setId(((Teacher)invocationOnMock.getArguments()[0]).getId());
                return teacherDTO;
            });
//            .thenReturn(new TeacherDTO());

        when(userRepository.findByLoginUserId())
            .thenReturn(UserProvider.getUser());
    }

    @Test
    public void shouldFindUser() throws Exception {
//        User user = userService.findByLoginUserId()
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

//    @Test
//    public void saveTeacherAndUser() throws Exception {
//    }

    @Test
    public void findAll() throws Exception {
        List<TeacherDTO> teacherDTOS = teacherService.findAll();
        assertNotNull(teacherDTOS);
        assertTrue(!teacherDTOS.isEmpty());
    }

    @Test
    public void findOne() throws Exception {
        TeacherDTO teacher = teacherService.findOne(3L);
        assertNotNull(teacher);
        assertEquals(new Long(10L), teacher.getId());
    }

//    @Test
//    public void findTeacherByCurrentUser() throws Exception {
//    }

    @Test
    public void delete() throws Exception {
        Long teacherId = 5L;
        TeacherDTO teacherDTO = teacherService.findOne(teacherId);
        assertNotNull(teacherDTO);
        teacherService.delete(teacherDTO.getId());
        TeacherDTO maybeTeacherDTO = teacherService.findOne(teacherId);
        assertNull(maybeTeacherDTO);
    }

//    @Test
//    public void saveTeacherWithUser() throws Exception {
//    }
//
//    @Test
//    public void saveHeadTeacherWithUser() throws Exception {
//    }
//
//    @Test
//    public void findAllByCurrentSchool() throws Exception {
//        Long schoolId = school.getId();
//        List<TeacherDTO> teacherDTOS = teacherService.findAllByCurrentSchool();
//
//    }
//
//    @Test
//    public void getAllBySchoolId() throws Exception {
//    }
//
//    @Test
//    public void makeHeadTeacher() throws Exception {
//    }
//
//    @Test
//    public void unMakeHeadTeacher() throws Exception {
//    }

}
