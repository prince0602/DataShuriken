package com.example.SupaDataShuriken.service;


import com.example.SupaDataShuriken.dto.UserDtoRequest;
import com.example.SupaDataShuriken.dto.UserDtoResponse;
import com.example.SupaDataShuriken.entity.UserEntity;
import com.example.SupaDataShuriken.exception.ResourceNotFoundException;
import com.example.SupaDataShuriken.repository.UserRepo;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Builder
public class UserService {
    @Autowired
    private UserRepo userRepository;


    public UserDtoResponse addUser(UserDtoRequest request) {
        UserEntity user=new UserEntity();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        user.setDesignation(request.getDesignation());
        String workEx = String.join("|", user.getWorkExperiences());
        user.setWorkExperiences(workEx);
        user.setAboutMe(request.getAboutMe());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanyId(request.getCompanyId());

        userRepository.save(user);
        return convertToUserResponse(user);
    }

    private UserDtoResponse convertToUserResponse(UserEntity user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(user.getId());
        userDtoResponse.setUserName(user.getUserName());
        userDtoResponse.setProfilePictureUrl(user.getProfilePictureUrl());
        userDtoResponse.setDesignation(user.getDesignation());
        List<String> workExperiences = Arrays.asList(user.getWorkExperiences().split("\\|"));
        userDtoResponse.setWorkExperiences(workExperiences);
        userDtoResponse.setAboutMe(user.getAboutMe());
        userDtoResponse.setEmail(user.getEmail());
        userDtoResponse.setPhoneNumber(user.getPhoneNumber());
        userDtoResponse.setCompanyId(user.getCompanyId());
        return userDtoResponse;

    }

    public UserDtoResponse findUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return convertToUserResponse(user);
    }
}

