package com.example.SupaDataShuriken.repository;

import com.example.SupaDataShuriken.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {
}
