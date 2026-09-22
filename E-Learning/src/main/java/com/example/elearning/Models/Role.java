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
    private UUID roleId;

    @Column(name="Role_Name")
    private String roleName;


    //Role belongs to many users:
    @OneToMany(mappedBy = "role")
    List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(UUID roleId, String roleName, List<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public UUID getRoleId() {
        return  this.roleId ;
    }

    public void setRoleId(UUID roleId) {
        this.roleId  = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }




}
