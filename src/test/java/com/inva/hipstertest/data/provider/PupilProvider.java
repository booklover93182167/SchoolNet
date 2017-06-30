package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.Parent;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@Component
public class PupilProvider {

    @Autowired
    private PupilRepository pupilRepository;

    public static Pupil getPupil() {
        Set<Parent> parents = new HashSet<>();
        return Pupil.builder()
            .enabled(true)
            .user(UserProvider.getUser())
            .form(FormProvider.getForm())
            .parents(parents)
            .build();
    }

    public Pupil persistPupil(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    public Pupil persistPupilDefault() {
        return pupilRepository.save(getPupil());
    }
}
