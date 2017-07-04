package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.SchoolNetApp;
import com.inva.hipstertest.data.provider.UserProvider;
import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.service.BaseServiceTest;
import com.inva.hipstertest.service.ParentService;
import com.inva.hipstertest.service.PupilService;
import com.inva.hipstertest.service.dto.ParentDTO;
import com.inva.hipstertest.service.dto.PupilDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by slavkosoltys on 28.06.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolNetApp.class)
@Transactional
public class PupilServiceImplTest extends BaseServiceTest {

    @Autowired
    private PupilService pupilService;

    private User user;
    private Form form;
    private Pupil pupil;
    private School school;
    private Parent parent;

    @Before
    public void setUp() throws Exception {
        school = schoolProvider.persistSchoolDefault();
        form = formProvider.persistFormWithSchool(school);
        user = userProvider.persistUserDefault();
//        pupil = pupilProvider.persistPupilWithUser(user, form);
        parent = parentProvider.persistParentWithUser(user);
    }

    @Test
    public void shouldSave() throws Exception {
        // arrange
        PupilDTO pupilDTO = PupilDTO.builder()
            .enabled(true)
            .formId(form.getId())
            .userId(user.getId())
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
        Long pupilId = pupil.getId();
        PupilDTO pupil = pupilService.findOne(pupilId);
        assertNotNull(pupil);
        assertEquals(pupilId, pupil.getId());
    }

    @Test
    public void shouldDelete() throws Exception {
        Long pupilId = 4L;
        PupilDTO pupil = pupilService.findOne(pupilId);
        assertNotNull(pupil);
        pupilService.delete(pupilId);
        PupilDTO maybePupil = pupilService.findOne(pupilId);
        assertNull(maybePupil);
    }

    @Test
    public void shouldFindAllByFormId() throws Exception {
        Long formId = form.getId();
        List<PupilDTO> pupils = pupilService.findAllByFormId(formId);
        assertNotNull(pupils);
        assertTrue(!pupils.isEmpty());
        for (PupilDTO pupil :
            pupils) {
            assertNotNull(pupil);
            assertTrue(formId.equals(pupil.getFormId()));
        }
    }

    @Test
    public void shouldFindAllByParentId() throws Exception {
        List<PupilDTO> pupils = pupilService.findAllByParentId(parent.getId());
        assertNotNull(pupils);
        for (PupilDTO pupil : pupils) {
            assertTrue(pupil.getParents().contains(parent));
        }
    }
}
