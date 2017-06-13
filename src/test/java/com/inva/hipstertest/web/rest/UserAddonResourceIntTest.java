package com.inva.hipstertest.web.rest;

import com.inva.hipstertest.SchoolNetApp;

import com.inva.hipstertest.domain.UserAddon;
import com.inva.hipstertest.repository.UserAddonRepository;
import com.inva.hipstertest.service.UserAddonService;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import com.inva.hipstertest.service.mapper.UserAddonMapper;
import com.inva.hipstertest.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserAddonResource REST controller.
 *
 * @see UserAddonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolNetApp.class)
public class UserAddonResourceIntTest {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private UserAddonRepository userAddonRepository;

    @Autowired
    private UserAddonMapper userAddonMapper;

    @Autowired
    private UserAddonService userAddonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserAddonMockMvc;

    private UserAddon userAddon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserAddonResource userAddonResource = new UserAddonResource(userAddonService);
        this.restUserAddonMockMvc = MockMvcBuilders.standaloneSetup(userAddonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserAddon createEntity(EntityManager em) {
        UserAddon userAddon = new UserAddon()
            .phone(DEFAULT_PHONE)
            .middleName(DEFAULT_MIDDLE_NAME)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return userAddon;
    }

    @Before
    public void initTest() {
        userAddon = createEntity(em);
    }

    @Ignore
    @Test
    @Transactional
    public void createUserAddon() throws Exception {
        int databaseSizeBeforeCreate = userAddonRepository.findAll().size();

        // Create the UserAddon
        UserAddonDTO userAddonDTO = userAddonMapper.toDto(userAddon);
        restUserAddonMockMvc.perform(post("/api/user-addons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAddonDTO)))
            .andExpect(status().isCreated());

        // Validate the UserAddon in the database
        List<UserAddon> userAddonList = userAddonRepository.findAll();
        assertThat(userAddonList).hasSize(databaseSizeBeforeCreate + 1);
        UserAddon testUserAddon = userAddonList.get(userAddonList.size() - 1);
        assertThat(testUserAddon.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserAddon.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testUserAddon.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testUserAddon.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createUserAddonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userAddonRepository.findAll().size();

        // Create the UserAddon with an existing ID
        userAddon.setId(1L);
        UserAddonDTO userAddonDTO = userAddonMapper.toDto(userAddon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserAddonMockMvc.perform(post("/api/user-addons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAddonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserAddon> userAddonList = userAddonRepository.findAll();
        assertThat(userAddonList).hasSize(databaseSizeBeforeCreate);
    }

    @Ignore
    @Test
    @Transactional
    public void getAllUserAddons() throws Exception {
        // Initialize the database
        userAddonRepository.saveAndFlush(userAddon);

        // Get all the userAddonList
        restUserAddonMockMvc.perform(get("/api/user-addons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userAddon.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }

    @Ignore
    @Test
    @Transactional
    public void getUserAddon() throws Exception {
        // Initialize the database
        userAddonRepository.saveAndFlush(userAddon);

        // Get the userAddon
        restUserAddonMockMvc.perform(get("/api/user-addons/{id}", userAddon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userAddon.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    public void getNonExistingUserAddon() throws Exception {
        // Get the userAddon
        restUserAddonMockMvc.perform(get("/api/user-addons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Ignore
    @Test
    @Transactional
    public void updateUserAddon() throws Exception {
        // Initialize the database
        userAddonRepository.saveAndFlush(userAddon);
        int databaseSizeBeforeUpdate = userAddonRepository.findAll().size();

        // Update the userAddon
        UserAddon updatedUserAddon = userAddonRepository.findOne(userAddon.getId());
        updatedUserAddon
            .phone(UPDATED_PHONE)
            .middleName(UPDATED_MIDDLE_NAME)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        UserAddonDTO userAddonDTO = userAddonMapper.toDto(updatedUserAddon);

        restUserAddonMockMvc.perform(put("/api/user-addons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAddonDTO)))
            .andExpect(status().isOk());

        // Validate the UserAddon in the database
        List<UserAddon> userAddonList = userAddonRepository.findAll();
        assertThat(userAddonList).hasSize(databaseSizeBeforeUpdate);
        UserAddon testUserAddon = userAddonList.get(userAddonList.size() - 1);
        assertThat(testUserAddon.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserAddon.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testUserAddon.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testUserAddon.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Ignore
    @Test
    @Transactional
    public void updateNonExistingUserAddon() throws Exception {
        int databaseSizeBeforeUpdate = userAddonRepository.findAll().size();

        // Create the UserAddon
        UserAddonDTO userAddonDTO = userAddonMapper.toDto(userAddon);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserAddonMockMvc.perform(put("/api/user-addons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAddonDTO)))
            .andExpect(status().isCreated());

        // Validate the UserAddon in the database
        List<UserAddon> userAddonList = userAddonRepository.findAll();
        assertThat(userAddonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Ignore
    @Test
    @Transactional
    public void deleteUserAddon() throws Exception {
        // Initialize the database
        userAddonRepository.saveAndFlush(userAddon);
        int databaseSizeBeforeDelete = userAddonRepository.findAll().size();

        // Get the userAddon
        restUserAddonMockMvc.perform(delete("/api/user-addons/{id}", userAddon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserAddon> userAddonList = userAddonRepository.findAll();
        assertThat(userAddonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAddon.class);
        UserAddon userAddon1 = new UserAddon();
        userAddon1.setId(1L);
        UserAddon userAddon2 = new UserAddon();
        userAddon2.setId(userAddon1.getId());
        assertThat(userAddon1).isEqualTo(userAddon2);
        userAddon2.setId(2L);
        assertThat(userAddon1).isNotEqualTo(userAddon2);
        userAddon1.setId(null);
        assertThat(userAddon1).isNotEqualTo(userAddon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAddonDTO.class);
        UserAddonDTO userAddonDTO1 = new UserAddonDTO();
        userAddonDTO1.setId(1L);
        UserAddonDTO userAddonDTO2 = new UserAddonDTO();
        assertThat(userAddonDTO1).isNotEqualTo(userAddonDTO2);
        userAddonDTO2.setId(userAddonDTO1.getId());
        assertThat(userAddonDTO1).isEqualTo(userAddonDTO2);
        userAddonDTO2.setId(2L);
        assertThat(userAddonDTO1).isNotEqualTo(userAddonDTO2);
        userAddonDTO1.setId(null);
        assertThat(userAddonDTO1).isNotEqualTo(userAddonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userAddonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userAddonMapper.fromId(null)).isNull();
    }
}
