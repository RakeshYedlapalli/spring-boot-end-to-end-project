package com.endtoend.springboot.ui.springbootendtoendapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.endtoend.springboot.ui.springbootendtoendapp.common.Constants.USER_DETAILS;

@Configuration
public class KafkaConfig {



    public NewTopic topic() {
        return TopicBuilder.name(USER_DETAILS)
                .build();
    }

}
