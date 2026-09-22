package com.example.elearning.Repos;

import com.example.elearning.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {


    //Find Role by role name

    public Role findRoleByRoleName(String roleName);
}
