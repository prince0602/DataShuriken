package com.example.SupaDataShuriken.dto;

import com.example.SupaDataShuriken.entity.CompanyMetaData;
import lombok.Data;

@Data
public class CompanyDtoRequest {


    private String name;
    private String officialContact;
    private String officialEmail;
    private String websiteUrl;
    private CompanyMetaData companyMetaData;
}
