package com.examly.springapp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import java.util.ArrayList;

// //@Service
// public class MyUserDetailsService implements UserDetailsService {

//     private final UserRepo userRepo;

//     public MyUserDetailsService(UserRepo userRepo) {
//         this.userRepo = userRepo;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepo.findByUsername(username); // Fetch the user
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found with username: " + username);
//         }
//         // Convert User to UserDetails
//         return new org.springframework.security.core.userdetails.User(
//             user.getUsername(),
//             user.getPassword(),
//             new ArrayList<>() // Add roles/authorities if needed later
//         );
//     }
// }