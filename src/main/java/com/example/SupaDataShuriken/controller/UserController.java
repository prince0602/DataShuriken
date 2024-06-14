package com.example.SupaDataShuriken.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SupaDataShuriken.dto.UserDtoRequest;
import com.example.SupaDataShuriken.dto.UserDtoResponse;
import com.example.SupaDataShuriken.dto.UsersRequest;
import com.example.SupaDataShuriken.entity.Users;
import com.example.SupaDataShuriken.exception.ResourceNotFoundException;
import com.example.SupaDataShuriken.repository.UsersRepository;
import com.example.SupaDataShuriken.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UsersRepository userRepository;
	
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
    
    @PostMapping("/createUsers")
    public ResponseEntity<List<String>> createUsers(@RequestParam("file") MultipartFile file) {
        List<String> failedEntries = new ArrayList<>();
        List<UsersRequest> userRequests = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    UsersRequest userRequest = new UsersRequest();
                    userRequest.setFullName(fields[0]);
                    userRequest.setEmail(fields[1]);
                    userRequest.setContactNumber(fields[2]);
                    userRequest.setCompanyId(fields[3]);
                    userRequests.add(userRequest);
                } else {
                    failedEntries.add("Invalid format: " + line);
                }
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error reading CSV file"));
        }

        for (UsersRequest userRequest : userRequests) {
            if (!isValidUserRequest(userRequest)) {
                failedEntries.add(userRequest.toString());
            }
        }

        if (failedEntries.isEmpty()) {
            for (UsersRequest userRequest : userRequests) {
                Users user = new Users();
                user.setCompanyId(userRequest.getCompanyId());
                user.setContactNumber(userRequest.getContactNumber());
                user.setEmail(userRequest.getEmail());
                user.setFullName(userRequest.getFullName());
                userRepository.save(user);
            }
        }

        return ResponseEntity.ok(failedEntries);
    }

    private boolean isValidUserRequest(UsersRequest userRequest) {
        return userRequest != null &&
                isValidEmail(userRequest.getEmail()) &&
                isValidContactNumber(userRequest.getContactNumber()) &&
                isValidCompanyId(userRequest.getCompanyId()) &&
                userRequest.getFullName() != null && !userRequest.getFullName().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(".+@.+\\..+");
    }

    private boolean isValidContactNumber(String contactNumber) {
        return contactNumber != null && contactNumber.matches("\\d{10}");
    }

    private boolean isValidCompanyId(String companyId) {
        return companyId != null && companyId.matches("[a-zA-Z0-9]{8}");
    }
}
