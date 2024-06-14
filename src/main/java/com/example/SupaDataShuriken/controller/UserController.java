package com.example.SupaDataShuriken.controller;

import com.example.SupaDataShuriken.dto.UserDtoRequest;
import com.example.SupaDataShuriken.dto.UserDtoResponse;
import com.example.SupaDataShuriken.exception.ResourceNotFoundException;
import com.example.SupaDataShuriken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/")
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDtoRequest request) {

        UserDtoResponse response = service.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {

        try {
            UserDtoResponse response = service.findUserById(userId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
