package com.example.SupaDataShuriken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SupaDataShuriken.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{

}
