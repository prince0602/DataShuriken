package com.example.SupaDataShuriken.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserDtoResponse {
    private Long id;

    private String userName;
    private String profilePictureUrl;
    private String aboutMe;
    private List<CompanyDto> companies;
    private int connections;
    private String email;
    private String phoneNumber;
}
