package com.endtoend.springboot.ui.springbootendtoendapp.login.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsDto implements Serializable {

    private Integer userId;
    private String username;
    private String firstName;
    private String lastName;
    private String dob;
    private String password;
}
