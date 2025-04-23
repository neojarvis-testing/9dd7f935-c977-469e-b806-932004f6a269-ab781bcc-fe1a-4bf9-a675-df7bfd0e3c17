package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public interface UserService {
  public User createUser(User user);
  public User loadUserByUsername(String userName);
  public List<User> findAllUsers();
  public LoginDTO loginUser(User user);
  public User getById(long id);
  public void deleteUser(long id);
  public Optional<User> updateUser(User user);
  public User getUserbyName(String name);
}
