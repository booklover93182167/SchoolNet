package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by slavkosoltys on 29.06.17.
 */
@Component
@Transactional
public class SchoolProvider {

    @Autowired
    private SchoolRepository schoolRepository;

    public static School getSchool() {
        return School.builder()
            .enabled(true)
            .name("4")
            .build();
    }

    public School persistSchool(School school) {
        return schoolRepository.save(school);
    }

    public School persistSchoolDefault() {
        return schoolRepository.save(getSchool());
    }

}
