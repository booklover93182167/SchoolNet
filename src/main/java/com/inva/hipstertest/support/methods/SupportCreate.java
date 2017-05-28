package com.inva.hipstertest.support.methods;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.repository.UserRepository;
import com.inva.hipstertest.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sensei on 23.05.2017.
 */
//@Component
abstract public class SupportCreate {


    private final Logger log = LoggerFactory.getLogger(SupportCreate.class);
    @Autowired
    private PasswordEncoder encode;
    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param paramFromPage must have:
     * firstName, lastName, email.
     * @param role [teacher, pupil, headTeacher, parent]
     * */
    public Map<String, Object> saveUserWithRole(User paramFromPage, String role){
        log.debug("Request to save user", paramFromPage, role);
        User user = new User();
        String login =  RandomUtil.generateLogin(paramFromPage.getFirstName(), paramFromPage.getLastName());
        user.setLogin(login);
        user.setFirstName(paramFromPage.getFirstName());
        user.setLastName(paramFromPage.getLastName());
        user.setEmail(paramFromPage.getEmail());
        user.setLangKey("en");
        /* For user what we need use true */
        user.setActivated(true);
        Set<Authority> auto = new HashSet<>();
        Authority authority = new Authority();
        if(role.equals("headTeacher")){ //enum
            authority.setName("ROLE_HEAD_TEACHER");
            auto.add(authority);
        }else if(role.equals("teacher")){
            authority.setName("ROLE_TEACHER");
            auto.add(authority);
        }else if(role.equals("pupil")){
            authority.setName("ROLE_PUPIL");
            auto.add(authority);
            user.setActivated(false);
        }else if(role.equals("parent")){
            authority.setName("ROLE_PARENT");
            auto.add(authority);
            user.setActivated(false);
        }else{
            System.out.println("BAD PARAM(role) IN METHOD saveUserWithRole");
            authority.setName("ROLE_USER");
            auto.add(authority);
            user.setActivated(false);
        }
        user.setAuthorities(auto);
        String noEncryptedPassword = RandomUtil.generatePassword();
        String encryptedPassword = encode.encode(noEncryptedPassword);
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(ZonedDateTime.now());
        user = userRepository.save(user);
        String content = "Your login: (" + login + "). And password: (" + noEncryptedPassword + ").";
        Map<String, Object> map = new HashMap<>();
        map.put("id", user);
        map.put("content", content);
        return map;
    }


}
