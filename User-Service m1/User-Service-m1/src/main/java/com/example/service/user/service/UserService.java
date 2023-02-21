package com.example.service.user.service;

import com.example.service.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //user operation
    //create

    User saveUser(User user);
    //
    //get  all user
    List<User> getAllUser();
    //get single user from list
    User getUser(String userId);

}
