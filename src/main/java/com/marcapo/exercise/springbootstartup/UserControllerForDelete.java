package com.marcapo.exercise.springbootstartup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerForDelete {
    private final UserService userService;

    @Autowired
    public UserControllerForDelete(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/search/deleteByUsername")
    public ResponseEntity<String> deleteByUsername(@RequestParam("username") String username) {

        boolean deleted = userService.deleteByUsername(username);

        if (deleted) {
            return ResponseEntity.ok("user: '" + username + "' is successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}