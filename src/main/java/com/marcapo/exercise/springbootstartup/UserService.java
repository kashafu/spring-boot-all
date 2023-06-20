package com.marcapo.exercise.springbootstartup;

import com.marcapo.exercise.springbootstartup.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        user.setPassword(Utils.md5Hash(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }



}