package com.example.SupaDataShuriken.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String profilePictureUrl;
    private String aboutMe;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CompanyEntity> companies;

    private int connections;
    private String email;
    private String phoneNumber;
}
