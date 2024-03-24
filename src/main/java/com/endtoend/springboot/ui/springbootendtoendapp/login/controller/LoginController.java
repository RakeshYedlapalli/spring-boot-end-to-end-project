package com.endtoend.springboot.ui.springbootendtoendapp.login.controller;


import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.LoginDetailsDto;
import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.UserDetailsDto;
import com.endtoend.springboot.ui.springbootendtoendapp.login.response.UserLoginResponse;
import com.endtoend.springboot.ui.springbootendtoendapp.login.response.UserRegistrationResponse;
import com.endtoend.springboot.ui.springbootendtoendapp.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping
    public UserRegistrationResponse registerUser(@RequestBody UserDetailsDto userDetailsDto) {
        UserDetailsDto dto = loginService.save(userDetailsDto);

        UserRegistrationResponse userRegistrationResponse;
        if (dto != null) {
            userRegistrationResponse = new UserRegistrationResponse(true);
        } else {
            userRegistrationResponse = new UserRegistrationResponse(false);
        }
        return userRegistrationResponse;

    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody LoginDetailsDto loginDetailsDto) {
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

        return userLoginResponse;
    }
}
