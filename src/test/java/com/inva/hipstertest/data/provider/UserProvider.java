package com.inva.hipstertest.data.provider;

import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by slavkosoltys on 29.06.17.
 */
@Component
@Transactional
public class UserProvider {

    @Autowired
    private UserRepository userRepository;

    public static User getUser() {
        return User.builder()
            .activated(true)
            .login("test")
            .password("test")
            .build();
    }

    public User persistUserDefault(){
        return userRepository.save(getUser());
    }
}
