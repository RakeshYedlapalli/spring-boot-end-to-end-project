package com.endtoend.springboot.ui.springbootendtoendapp.login.controller;


import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.LoginDetailsDto;
import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.UserDetailsDto;
import com.endtoend.springboot.ui.springbootendtoendapp.login.repository.UserDetailsRepository;
import com.endtoend.springboot.ui.springbootendtoendapp.login.response.UserLoginResponse;
import com.endtoend.springboot.ui.springbootendtoendapp.login.response.UserRegistrationResponse;
import com.endtoend.springboot.ui.springbootendtoendapp.login.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserDetailsRepository detailsRepository;


    @PostMapping
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserDetailsDto userDetailsDto)
            throws JsonProcessingException {
        System.out.println(detailsRepository.hashCode());
        UserDetailsDto dto = loginService.save(userDetailsDto);

        UserRegistrationResponse userRegistrationResponse;
        if (dto != null) {
            userRegistrationResponse = new UserRegistrationResponse(true);
        } else {
            userRegistrationResponse = new UserRegistrationResponse(false);
        }
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginDetailsDto loginDetailsDto) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        boolean isSuccess =
                loginService.findUserDetailsByUserName(loginDetailsDto.getUsername(), loginDetailsDto.getPassword());
        if (isSuccess) {
            userLoginResponse.setLoginStatus("Success");
            userLoginResponse.setUsername(loginDetailsDto.getUsername());
        } else {
            userLoginResponse.setLoginStatus("User Does not exists");
            userLoginResponse.setUsername(loginDetailsDto.getUsername());
        }
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }


    @GetMapping("/isResponsive")
    public CompletableFuture<String> responsive() throws InterruptedException {
        return asyncHello();
    }


    //    @Async
    public CompletableFuture<String> asyncHello() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            // Simulating some asynchronous task
            try {
                Thread.sleep(10000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("Hello from async method!");
        }).start();
        return future;
    }
}

