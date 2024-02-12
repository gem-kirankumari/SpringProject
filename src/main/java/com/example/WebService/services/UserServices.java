package com.example.WebService.services;

import com.example.WebService.model.UserModel;
import com.example.WebService.repository.UserRepository;
import com.example.WebService.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    public boolean createUser(UserModel user) {
        return userRepository.save(user) != null ? true : false;
    }


    public Map<String, Object> loginUser(UserModel mappedUser) {
        UserModel user = userRepository.findByNameIgnoreCaseAndPassword(mappedUser.getName(), mappedUser.getPassword());
        log.info(user.getName());
        Map<String, Object> jwtToken = new HashMap<>();

        if (user != null) {
//            log.info(jwtService.createToken(user));
            jwtToken.put("token", jwtService.createToken(user));
            return jwtToken;
        }
        return null;
    }
}
