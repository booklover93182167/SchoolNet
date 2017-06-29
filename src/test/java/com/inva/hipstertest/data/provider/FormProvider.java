package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.Form;
import com.inva.hipstertest.domain.School;
import com.inva.hipstertest.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by slavkosoltys on 29.06.17.
 */
@Component
@Transactional
public class FormProvider {

    @Autowired
    private FormRepository formRepository;

    public static Form getForm() {
        return Form.builder()
            .enabled(true)
            .name("5b")
            .school(SchoolProvider.getSchool())
            .build();
    }

    public Form persistFormDefault() {
        return formRepository.save(getForm());
    }

    public Form persistFormWithSchool(School school) {
        return formRepository.save(Form.builder()
            .enabled(true)
            .name("5b")
            .school(school)
            .build());
    }
}
