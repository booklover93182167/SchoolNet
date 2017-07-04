package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.Parent;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.repository.ParentRepository;
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
public class ParentProvider {

    @Autowired
    private ParentRepository parentRepository;

    public static Parent getParent() {
        Set<Pupil> pupils = new HashSet<>();
        return Parent.builder()
            .enabled(true)
            .user(UserProvider.getUser())
            .pupils(pupils)
            .build();
    }

    public Parent persistParentWithUser(User user){
        Set<Pupil> pupils = new HashSet<>();
        return parentRepository.save(Parent.builder()
        .enabled(true)
        .user(user)
        .pupils(pupils)
        .build());
    }

    public Parent persisteParentDefault() {
        return parentRepository.save(getParent());
    }
}
