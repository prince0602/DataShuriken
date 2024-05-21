package com.example.SupaDataShuriken.service;

import com.example.SupaDataShuriken.dto.CompanyDtoRequest;
import com.example.SupaDataShuriken.dto.CompanyDtoResponse;
import com.example.SupaDataShuriken.entity.CompanyEntity;
import com.example.SupaDataShuriken.entity.CompanyMetaData;
import com.example.SupaDataShuriken.exception.ResourceNotFoundException;
import com.example.SupaDataShuriken.repository.CompanyRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    public CompanyDtoResponse addCompany(CompanyDtoRequest request) {
        CompanyEntity company = new CompanyEntity();
        company.setName(request.getName());
        company.setOfficialContact(request.getOfficialContact());
        company.setOfficialEmail(request.getOfficialEmail());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setCompanyMetaData(convertToJSONString(request.getCompanyMetaData())); // Convert to JSON string

        companyRepo.save(company);
        return convertToCompanyResponse(company);
    }

    private String convertToJSONString(CompanyMetaData metaData) {
        try {
            return new ObjectMapper().writeValueAsString(metaData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CompanyDtoResponse convertToCompanyResponse(CompanyEntity company) {
        CompanyDtoResponse companyDtoResponse = new CompanyDtoResponse();
        companyDtoResponse.setCompanyId(company.getCompanyId());
        companyDtoResponse.setName(company.getName());
        companyDtoResponse.setOfficialContact(company.getOfficialContact());
        companyDtoResponse.setOfficialEmail(company.getOfficialEmail());
        companyDtoResponse.setWebsiteUrl(company.getWebsiteUrl());
        companyDtoResponse.setCompanyMetaData(convertToCompanyMetaData(company.getCompanyMetaData())); // Convert to CompanyMetaData object

        return companyDtoResponse;
    }

    private CompanyMetaData convertToCompanyMetaData(String metaDataJson) {
        try {
            return new ObjectMapper().readValue(metaDataJson, CompanyMetaData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompanyDtoResponse findCompanyById(Long companyId) {
        CompanyEntity company = companyRepo.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + companyId));

        return convertToCompanyResponse(company);
    }
}
