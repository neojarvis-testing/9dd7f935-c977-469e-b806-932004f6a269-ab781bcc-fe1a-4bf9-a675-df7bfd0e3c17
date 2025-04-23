package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.IncorrectEmailOrPasswordException;
import com.examly.springapp.exception.NoUserFoundException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRole("USER");
        return userRepo.save(user);
    }

    public LoginDTO loginUser(User user) {
        User checkUser= userRepo.findByUsername(user.getUsername());
        return new LoginDTO("Token",checkUser.getUsername(), checkUser.getUserRole(), checkUser.getUserId());
    }

    public User loadUserByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

   
    public List<User> findAllUsers() {
       List<User> user = userRepo.findAll();
       if(user.isEmpty())
          throw new NoUserFoundException("No user found!!");
        return user;
    }

   
    public User getById(long id) {
        User user = userRepo.findById(id).orElse(null);
        if(user==null)
           throw new NoUserFoundException("No user found!!");
        return user;
    }

  
    public void deleteUser(long id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
        }
    }

  
    public Optional<User> updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId()).orElse(null); 
        if(existingUser==null)
           throw new NoUserFoundException("No user found!!");
        return Optional.of(userRepo.save(user));
    }

  
    public User getUserbyName(String name) {
       User user = userRepo.findByUsername(name);
       if(user==null)
           throw new NoUserFoundException("No user found for this username  "+name);
        return user;
    }
    public LoginDTO loginUsers(User user) {
        User checkUser= userRepo.findByEmail(user.getEmail());
        if(checkUser==null)
            throw new NoUserFoundException("User Not Found!!");
        if(!encoder.matches(user.getPassword(),checkUser.getPassword()))
            throw new IncorrectEmailOrPasswordException("Password is Incorrect!!");
        return new LoginDTO("Token",checkUser.getUsername(), checkUser.getUserRole(), checkUser.getUserId());

    }}
