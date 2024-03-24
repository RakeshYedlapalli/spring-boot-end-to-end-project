package com.endtoend.springboot.ui.springbootendtoendapp.login.repository;

import com.endtoend.springboot.ui.springbootendtoendapp.login.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {


    UserDetails findByUsernameAndPassword(String username, String password);

}
