package com.example.WebService.controller;


import com.example.WebService.dto.UserDto;
import com.example.WebService.model.UserModel;
import com.example.WebService.services.UserServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserServices userServices;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid UserDto userDto) {
        UserModel mappedUser = modelMapper.map(userDto, UserModel.class);
        return userServices.createUser(mappedUser) ? new ResponseEntity<>("User created successfully", HttpStatus.CREATED) : new ResponseEntity<>("Operation Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto userDto) {
        log.info("In login");
        UserModel mappedUser = modelMapper.map(userDto, UserModel.class);
        return userServices.loginUser(mappedUser) != null ? new ResponseEntity<>(userServices.loginUser(mappedUser), HttpStatus.OK) : new ResponseEntity<>("Bad Credentials", HttpStatus.UNAUTHORIZED);
    }
}
