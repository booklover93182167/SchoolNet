package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@Component
@Transactional
public class PupilProvider {

    @Autowired
    private PupilRepository pupilRepository;

//    public static Pupil getPupil() {
////        Set<Parent> parents = new HashSet<>();
//        return Pupil.builder()
//            .enabled(true)
//            .user(UserProvider.getUser())
//            .form(FormProvider.getForm())
////            .parents(parents)
//            .build();
//    }

    public Pupil persistPupil(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    public Pupil persistPupilWithUser(User user, Form form) {
        Set<Parent> parents = new HashSet<>();
        parents.add(ParentProvider.getParent());
        return pupilRepository.save(Pupil.builder()
            .enabled(true)
            .user(user)
            .form(form)
            .parents(parents)
            .build());
    }


//    public Pupil persistPupilWithUserParentForm(User user, Parent parent, Form form){
//        Set<Parent> parents = new HashSet<>();
//        parents.add(parent);
//        return pupilRepository.save(Pupil.builder()
//            .enabled(true)
//            .user(user)
//            .form(form)
//            .parents(parents)
//            .build());
//    }
}
