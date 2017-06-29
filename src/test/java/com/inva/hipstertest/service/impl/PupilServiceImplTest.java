package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.SchoolNetApp;
import com.inva.hipstertest.data.provider.FormProvider;
import com.inva.hipstertest.data.provider.SchoolProvider;
import com.inva.hipstertest.data.provider.UserProvider;
import com.inva.hipstertest.domain.Form;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.repository.FormRepository;
import com.inva.hipstertest.repository.SchoolRepository;
import com.inva.hipstertest.repository.UserRepository;
import com.inva.hipstertest.service.BaseServiceTest;
import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.ParentDTO;
import com.inva.hipstertest.service.dto.PupilDTO;
import com.inva.hipstertest.service.dto.UserDTO;
import org.aspectj.bridge.IMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collector;

import static org.junit.Assert.*;

/**
 * Created by slavkosoltys on 28.06.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolNetApp.class)
@Transactional
public class PupilServiceImplTest extends BaseServiceTest{

    @Autowired
    private PupilService pupilService;

    @Autowired
    private ParentService parentService;

    private User user;
    private School school;
    private Form form;

    @Before
    public void setUp() throws Exception {
        user = userProvider.persistUserDefault();
        school = schoolProvider.persistSchoolDefault();
        form = formProvider.persistFormWithSchool(school);
    }


    @Test
    public void shouldSave() throws Exception {
        // arrange
        Set<ParentDTO> parents = new HashSet<ParentDTO>();
        PupilDTO pupilDTO = PupilDTO.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .enabled(true)
            .formId(form.getId())
            .userId(user.getId())
            .parents(parents)
            .formName(form.getName())
            .build();

        // act
        PupilDTO savedPupilDTO = pupilService.save(pupilDTO);

        // assert
        assertNotNull(savedPupilDTO);
        assertEquals(pupilDTO.getFirstName(), savedPupilDTO.getFirstName());
    }

    @Test
    public void shouldFindAll() throws Exception {
        List<PupilDTO> pupils = pupilService.findAll();
        assertNotNull(pupils);
        assertTrue(!pupils.isEmpty());
    }

    @Test
    public void shouldFindOne() throws Exception {
        long pupilId = 3L;
        PupilDTO pupil = pupilService.findOne(pupilId);
        assertNotNull(pupil);
        assertEquals(new Long(pupilId), pupil.getId());

    }

    @Test
    public void shouldDelete() throws Exception {
        long pupilId = 3L;
        PupilDTO pupil = pupilService.findOne(pupilId);
        assertNotNull(pupil);
        pupilService.delete(pupilId);
        PupilDTO maybePupil = pupilService.findOne(pupilId);
        assertNull(maybePupil);
    }

    @Test
    public void shouldFindAllByFormId() throws Exception {
        Long formId = 4L;
        List<PupilDTO> pupils = pupilService.findAllByFormId(formId);
        assertNotNull(pupils);
        assertTrue(!pupils.isEmpty());
        PupilDTO pupilDTO = pupils.get(0);
        assertNotNull(pupilDTO);
        assertTrue(formId.equals(pupilDTO.getFormId()));
    }

    @Test
    public void shouldFindAllByParentId() throws Exception {
        ParentDTO parent = parentService.findOne(4L);
        List<PupilDTO> pupils = pupilService.findAllByParentId(parent.getId());
        assertNotNull(pupils);
        for (PupilDTO pupil : pupils) {
            assertTrue(pupil.getParents().contains(parent));
        }
    }

}
