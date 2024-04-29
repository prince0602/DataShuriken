package com.example.SupaDataShuriken.controller;

import com.example.SupaDataShuriken.dto.UserDtoRequest;
import com.example.SupaDataShuriken.dto.UserDtoResponse;
import com.example.SupaDataShuriken.entity.UserEntity;
import com.example.SupaDataShuriken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDtoRequest user) {
        UserEntity savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/getUserById")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
        UserDtoResponse response= userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
