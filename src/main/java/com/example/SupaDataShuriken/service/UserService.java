package com.example.SupaDataShuriken.service;

import com.example.SupaDataShuriken.dto.CompanyDto;
import com.example.SupaDataShuriken.dto.UserDtoRequest;
import com.example.SupaDataShuriken.dto.UserDtoResponse;
import com.example.SupaDataShuriken.entity.CompanyEntity;
import com.example.SupaDataShuriken.entity.UserEntity;
import com.example.SupaDataShuriken.repository.UserRepo;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class UserService {
    @Autowired
    private UserRepo userRepository;
    public UserEntity saveUser(UserDtoRequest user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUserName(user.getUserName());
        userEntity.setProfilePictureUrl(user.getProfilePictureUrl());
        userEntity.setAboutMe(user.getAboutMe());
        List<CompanyEntity> companyEntities = user.getCompanies().stream()
                .map(this::convertToCompanyEntity)
                .collect(Collectors.toList());
        userEntity.setCompanies(companyEntities);
        userEntity.setConnections(user.getConnections());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());

         userRepository.save(userEntity);
         return userEntity;


    }
    private CompanyEntity convertToCompanyEntity(CompanyDto companyDto) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(companyDto.getId());
        companyEntity.setName(companyDto.getName());
        companyEntity.setOfficialContact(companyDto.getOfficialContact());
        companyEntity.setOfficialEmail(companyDto.getOfficialEmail());
        companyEntity.setWebsiteUrl(companyDto.getWebsiteUrl());
        return companyEntity;
    }
    public UserDtoResponse getUserById(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        UserDtoResponse response = convertToUserDtoResponse(userEntity);
        return response;
    }
    private UserDtoResponse convertToUserDtoResponse(UserEntity userEntity) {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(userEntity.getId());
        response.setUserName(userEntity.getUserName());
        response.setProfilePictureUrl(userEntity.getProfilePictureUrl());
        response.setAboutMe(userEntity.getAboutMe());

        List<CompanyDto> companyDtos = userEntity.getCompanies().stream()
                .map(this::convertToCompanyDto)
                .collect(Collectors.toList());
        response.setCompanies(companyDtos);

        return response;
    }


    private CompanyDto convertToCompanyDto(CompanyEntity companyEntity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(companyEntity.getId());
        companyDto.setName(companyEntity.getName());
        companyDto.setOfficialContact(companyEntity.getOfficialContact());
        companyDto.setOfficialEmail(companyEntity.getOfficialEmail());
        companyDto.setWebsiteUrl(companyEntity.getWebsiteUrl());
        return companyDto;
    }
}
