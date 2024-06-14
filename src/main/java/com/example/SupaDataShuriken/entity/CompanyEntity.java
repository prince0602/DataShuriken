package com.example.SupaDataShuriken.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String name;
    private String officialContact;
    private String officialEmail;
    private String websiteUrl;

    @Column(columnDefinition = "jsonb")

    private String companyMetaData; // Store as JSON string

    // Transient field for storing CompanyMetaData object
//    @Transient
//    private CompanyMetaData metaDataObject;
//
//    // Getter and setter for companyMetaData
//    public String getCompanyMetaData() {
//        try {
//            return new ObjectMapper().writeValueAsString(metaDataObject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void setCompanyMetaData(String companyMetaData) {
//        try {
//            this.metaDataObject = new ObjectMapper().readValue(companyMetaData, CompanyMetaData.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            this.metaDataObject = null;
//        }
//    }

}
