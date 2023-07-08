package com.patroclos.springboot3.controller;

import com.patroclos.springboot3.models.User;
import com.patroclos.springboot3.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "User APIs")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @Operation(summary = "Get all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Create User")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get User by Username")
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {

        User user = userService.findByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete User by Username")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteByUsername(@PathVariable String username) {

        boolean deleted = userService.deleteByUsername(username);

        if (deleted) {
            return ResponseEntity.ok("user: '" + username + "' is successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}