package com.coding_hustle.journalApp.controller;

import com.coding_hustle.journalApp.entity.User;
import com.coding_hustle.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.findByUsername(user.getUserName()) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
