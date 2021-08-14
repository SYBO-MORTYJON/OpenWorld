package com.napa.OpenWorld.service;


import com.napa.OpenWorld.entities.User;
import com.napa.OpenWorld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServise implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    public User findUserById(Integer userId){
        Optional<User> userFromDB = userRepository.findById(userId);
        return userFromDB.orElse(new User());
    }
    public boolean saveUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        System.out.println(foundUser);
        if (foundUser !=null){
            return  false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(userRepository.save(user));
        return true;
    }
}