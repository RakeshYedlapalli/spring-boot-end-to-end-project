package com.endtoend.springboot.ui.springbootendtoendapp.login.service;


import ch.qos.logback.core.testUtil.RandomUtil;
import com.endtoend.springboot.ui.springbootendtoendapp.login.entity.UserDetails;
import com.endtoend.springboot.ui.springbootendtoendapp.login.repository.UserDetailsRepository;
import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public UserDetailsDto save(UserDetailsDto userDetailsDto) {
        userDetailsDto.setUserId(RandomUtil.getPositiveInt());
        UserDetails userDetails = new UserDetails(userDetailsDto.getUserId(),
                userDetailsDto.getUsername(), userDetailsDto.getFirstName(), userDetailsDto.getLastName(),
                userDetailsDto.getDob(), userDetailsDto.getPassword());
        userDetailsRepository.save(userDetails);
        return userDetailsDto;

    }

    public boolean findUserDetailsByUserName(String username, String password) {
        UserDetails userDetailsDto =
                userDetailsRepository.findByUsernameAndPassword(username, password);
        return userDetailsDto != null;

    }


}
