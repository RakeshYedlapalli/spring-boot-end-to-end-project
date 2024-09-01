package com.endtoend.springboot.ui.springbootendtoendapp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @KafkaListener(topics = "user-details",
            groupId = "user-group")
    public void userDetails(String username) {
        System.out.println(username);

    }
}
