package com.example.elearning.Repos;

import com.example.elearning.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    public Optional<User> findByEmail(String email);

}


