package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserServiceImpl service;
   @PostMapping("/register")
   public ResponseEntity<String> addUser(@RequestBody User user){
       user = service.createUser(user);
       return ResponseEntity.status(201).body("registered");
    }
    @PostMapping("/login")
    public ResponseEntity<?>loginUser(@RequestBody User user){
        LoginDTO loginDTO  = service.loginUser(user);
        user.setPassword(null);
        return ResponseEntity.status(200).body(loginDTO);
    }


    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> user = service.findAllUsers();
        return ResponseEntity.status(200).body(user);
    }
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> userById(@PathVariable long userId){
        service.deleteUser(userId);
       return ResponseEntity.status(200).body(null);
 }
 //Real API
 @PostMapping("/registers")
   public ResponseEntity<User> addNewUser(@RequestBody User user){
       user = service.createUser(user);
       return ResponseEntity.status(201).body(user);
    }
 @PostMapping("/logins")
    public ResponseEntity<LoginDTO> userLogins(@RequestBody User user){
        LoginDTO userLogin = service.loginUsers(user);
        return ResponseEntity.status(200).body(userLogin);
    }

}
