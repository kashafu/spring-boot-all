package com.patroclos.springboot3.service;

import com.patroclos.springboot3.models.User;
import com.patroclos.springboot3.repository.UserRepository;
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
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        } else {
            return false;
        }
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