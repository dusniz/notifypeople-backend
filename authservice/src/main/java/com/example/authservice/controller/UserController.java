package com.example.authservice.controller;

import com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
//        return  userService
//                .findByUsername(username)
//                .map(user -> ResponseEntity.ok(user))
//                .orElseThrow(() -> new ResourceNotFoundException(username));
//    }

    @GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(userService.getAllUsers());
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity
                .ok(userService.findUserById(userId));
    }
}