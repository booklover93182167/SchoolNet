package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.Teacher;
import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.repository.TeacherRepository;
import com.inva.hipstertest.repository.UserRepository;
import com.inva.hipstertest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by slavkosoltys on 29.06.17.
 */
@Component
@Transactional
public class TeacherProvider {

    @Autowired
    private TeacherRepository teacherRepository;

    public static Teacher getTeacher() {
        return Teacher.builder()
            .enabled(true)
            .form(FormProvider.getForm())
            .user(UserProvider.getUser())
            .build();
    }

    public static Teacher getTeacher(Long id) {
        return Teacher.builder()
            .id(id)
            .enabled(true)
            .form(FormProvider.getForm())
            .user(UserProvider.getUser())
            .build();
    }

    public Teacher persistTeacherDefault(){
        return teacherRepository.save(getTeacher());
    }
}
