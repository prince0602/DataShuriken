package com.example.SupaDataShuriken.dto;

import com.example.SupaDataShuriken.entity.CompanyMetaData;
import lombok.Data;

@Data
public class CompanyDtoResponse {
    private Long companyId;

    private String name;
    private String officialContact;
    private String officialEmail;
    private String websiteUrl;

    private CompanyMetaData companyMetaData;
}
