package com.example.elearning.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="SYS_ROLE")
public class Role {

    @Id
    @Column(name="Role_Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID RoleId;

    @Column(name="Role_Name")
    private String RoleName;


    //Role belongs to many users:
    @OneToMany(mappedBy = "role")
    List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(UUID roleId, String roleName, List<User> users) {
        RoleId = roleId;
        RoleName = roleName;
        this.users = users;
    }

    public UUID getRoleId() {
        return RoleId;
    }

    public void setRoleId(UUID roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }




}
