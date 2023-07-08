package com.spring.app.service;

import com.spring.app.models.User;
import com.spring.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ...

    public boolean deleteByUsername(String username) {
        if (existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        } else {
            return false;
        }
    }
    public boolean existsByUsername(String username) {
      return userRepository.existsByUsername(username) ;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email) ;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user) {
//        user.setPassword(Utils.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
      return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }



}