package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.exception.IncorrectEmailOrPasswordException;
import com.examly.springapp.exception.NoUserFoundException;
import com.examly.springapp.mapper.UserMapper;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.RegisterDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

import jakarta.validation.Valid;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    // Constructor injection makes dependencies explicit and immutable.
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    //Dummy Service Methods
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    //Dummy Service Methods
    public LoginDTO loginUser(User user) {
        User checkUser= userRepo.findByUsername(user.getUsername());
        return new LoginDTO("token",checkUser.getUsername(), checkUser.getUserRole(), checkUser.getUserId());
    }



    public UserDetails loadUserByUsername(String username){
        System.out.println("User name is "+username);
      User user = userRepo.findByUsername(username);
      if(user==null){
        System.out.println("Error occures");
         throw new NoUserFoundException("User not found");
      }
      return UserPrinciple.build(user);
    }

   
    public List<User> findAllUsers() {
       List<User> user = userRepo.findAll();
       if(user.isEmpty())
          throw new NoUserFoundException("No user found!!");
        return user;
    }

    public LoginDTO loginUsers(User user) {
        // Find user by username
        User checkUser= userRepo.findByUsername(user.getUsername());
        if(checkUser==null)
            throw new NoUserFoundException("User Not Found!!");
        if(!encoder.matches(user.getPassword(),checkUser.getPassword()))
            throw new IncorrectEmailOrPasswordException("Password is Incorrect!!");
        return new LoginDTO("Token",checkUser.getUsername(), checkUser.getUserRole(), checkUser.getUserId());

    }
    public @Valid User addNewUser(RegisterDTO registerDTO) {
        registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));
        registerDTO.setUserRole("USER");
        User user = UserMapper.mapToUser(registerDTO);
        return userRepo.save(user);
    }
}
