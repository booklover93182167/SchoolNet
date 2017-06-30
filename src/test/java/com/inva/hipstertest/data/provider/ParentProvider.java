package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.Parent;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@Component
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

    public Parent persistParent(Parent parent){
        return parentRepository.save(parent);
    }

    public Parent persisteParentDefault(){
        return parentRepository.save(getParent());
    }
}
