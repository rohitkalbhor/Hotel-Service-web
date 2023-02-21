package com.example.service.user.controller;

import com.example.service.user.entity.User;
import com.example.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
   private  UserService userService;

    //create
    @PostMapping("/add")
    public ResponseEntity<User>createUser(@RequestBody User user){
       User User1 =userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(User1);
    }
    //single user get
    @GetMapping("/{userId}")
    public ResponseEntity<User>getSingleUser(@PathVariable String userId){
       User user= userService.getUser(userId);
       return ResponseEntity.ok(user);
    }
    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
