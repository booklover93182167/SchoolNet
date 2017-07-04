package com.inva.hipstertest.service;

import com.inva.hipstertest.repository.TeacherRepository;
import com.inva.hipstertest.repository.UserRepository;
import com.inva.hipstertest.service.mapper.TeacherMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by slavkosoltys on 03.07.17.
 */
@Configuration
public class TestContext {

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @Bean
    public TeacherRepository teacherRepository() {
        return mock(TeacherRepository.class);
    }

    @Bean
    public TeacherMapper teacherMapper() {
        return mock(TeacherMapper.class);
    }




}
