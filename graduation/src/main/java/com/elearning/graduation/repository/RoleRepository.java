package com.elearning.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.graduation.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
