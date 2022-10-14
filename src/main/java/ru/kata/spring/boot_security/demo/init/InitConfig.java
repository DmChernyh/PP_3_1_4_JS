package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitConfig {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitConfig(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");

    final Set<Role> roles1 = new HashSet<>(List.of(adminRole, userRole));
    final Set<Role> roles2 = new HashSet<>(List.of(userRole));

    final User admin = new User("admin@mail.ru", "admin", "java", "admin", roles1);
    final User user = new User("user@mail.ru", "user", "java", "user", roles2);

    public void init() {
        roleService.save(adminRole);
        roleService.save(userRole);
        userService.addUser(admin);
        userService.addUser(user);

    }
}
