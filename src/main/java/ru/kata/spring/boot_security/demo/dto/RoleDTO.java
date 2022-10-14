package ru.kata.spring.boot_security.demo.dto;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public class RoleDTO {

    private int id;

    private String role;

    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
