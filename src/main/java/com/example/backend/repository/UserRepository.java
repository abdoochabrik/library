package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByusername(String username);
     User findBypassword(String username);
}
