package com.endtoend.springboot.ui.springbootendtoendapp.login.service;


import ch.qos.logback.core.testUtil.RandomUtil;
import com.endtoend.springboot.ui.springbootendtoendapp.common.Constants;
import com.endtoend.springboot.ui.springbootendtoendapp.login.entity.UserDetails;
import com.endtoend.springboot.ui.springbootendtoendapp.login.repository.UserDetailsRepository;
import com.endtoend.springboot.ui.springbootendtoendapp.login.dto.UserDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LoginService {


    @Autowired
    private UserDetailsRepository userDetailsRepository;

//    @Autowired
//    private HelloWorld helloWorld;

    @Autowired
    UserDetailsRepository detailsRepository;




    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public UserDetailsDto save(UserDetailsDto userDetailsDto) throws JsonProcessingException {
        System.out.println(detailsRepository.hashCode());
//        helloWorld.nothing();
        userDetailsDto.setUserId(RandomUtil.getPositiveInt());
        UserDetails userDetails = new UserDetails(userDetailsDto.getUserId(),
                userDetailsDto.getUsername(), userDetailsDto.getFirstName(), userDetailsDto.getLastName(),
                userDetailsDto.getDob(), userDetailsDto.getPassword());
        userDetailsRepository.save(userDetails);

        publishUserDetails(userDetailsDto);

        return userDetailsDto;

    }

    private void publishUserDetails(UserDetailsDto userDetails) throws JsonProcessingException {

        CompletableFuture<SendResult<String, String>> completableFuture =
                kafkaTemplate.send(Constants.USER_DETAILS, new ObjectMapper().writeValueAsString(userDetails));

        System.out.println(completableFuture);
    }

    public boolean findUserDetailsByUserName(String username, String password) {
        UserDetails userDetailsDto =
                userDetailsRepository.findByUsernameAndPassword(username, password);
        return userDetailsDto != null;

    }


}
