package com.example.SupaDataShuriken.controller;

import com.example.SupaDataShuriken.dto.CompanyDtoRequest;
import com.example.SupaDataShuriken.dto.CompanyDtoResponse;
import com.example.SupaDataShuriken.exception.ResourceNotFoundException;
import com.example.SupaDataShuriken.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

//    @PostMapping("/saveUser")
//    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDtoRequest user) {
//        UserEntity savedUser = userService.saveUser(user);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }
//    @GetMapping("/getUserById/{userId}")
//    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
//        UserDtoResponse response= userService.getUserById(userId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @PutMapping("/editUser/{userId}")
//    public ResponseEntity<UserDtoResponse> editUser(@PathVariable Long userId, @RequestBody UserDtoRequest user) {
//        UserDtoResponse response = userService.editUser(userId, user);
//        if (response != null) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/deleteCompany/{userId}/{companyId}")
//    public ResponseEntity<String> deleteCompany(@PathVariable Long userId, @PathVariable Long companyId) {
//        boolean deleted = userService.deleteCompany(userId, companyId);
//        if (deleted) {
//            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Company not found or unable to delete", HttpStatus.NOT_FOUND);
//        }
//    }


    @PostMapping("/")
    public ResponseEntity<CompanyDtoResponse> addCompany(@RequestBody CompanyDtoRequest request) {

        CompanyDtoResponse response = companyService.addCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable("companyId") Long companyId) {

        try {
            CompanyDtoResponse response = companyService.findCompanyById(companyId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


}
