package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    List<Role> findAll();

    Role findRoleByRole(String role);

    RoleDTO convertToDto(Role role);

    Role findRoleById(int id);
}
