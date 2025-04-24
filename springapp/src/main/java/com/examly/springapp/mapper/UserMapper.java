package com.examly.springapp.mapper;

import com.examly.springapp.model.RegisterDTO;
import com.examly.springapp.model.User;

public class UserMapper {
    //Map User To UserDto
    public static RegisterDTO mapToRegisterDTO(User user){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUserId(user.getUserId()); 
        registerDTO.setEmail(user.getEmail());
        registerDTO.setPassword(user.getPassword());
        registerDTO.setMobileNumber(user.getMobileNumber());
        registerDTO.setUserRole(user.getUserRole());
        registerDTO.setUserId(user.getUserId());
        return registerDTO;
    }
     // Convert UserDTO to User
     public static User mapToUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUserId(registerDTO.getUserId());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setUsername(registerDTO.getUsername());
        user.setMobileNumber(registerDTO.getMobileNumber());
        user.setUserRole(registerDTO.getUserRole());
        return user;
    }
}
