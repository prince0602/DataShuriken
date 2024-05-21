package com.example.SupaDataShuriken.entity;

import lombok.Data;

import java.util.Map;

@Data
public class CompanyMetaData {

    private String backgroundColor;
    private Map<String, String> font;
    private Map<String, String> logo;
}
