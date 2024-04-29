package com.example.SupaDataShuriken.dto;

import lombok.Data;

@Data
public class CompanyDto {
    private Long id;

    private String name;
    private String officialContact;
    private String officialEmail;
    private String websiteUrl;
}
