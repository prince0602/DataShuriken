package com.example.SupaDataShuriken.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserDtoResponse {
    private Long id;

    private String userName;
    private String profilePictureUrl;
    private String designation;
    private List<String> workExperiences;
    private String aboutMe;
    private String email;
    private String phoneNumber;
    private Long companyId;
}
