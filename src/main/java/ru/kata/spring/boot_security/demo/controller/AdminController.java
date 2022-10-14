package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.exception.RegistrationWrongUsernameException;
import ru.kata.spring.boot_security.demo.exception.RegistrationWrongUsernameResponse;
import ru.kata.spring.boot_security.demo.exception.UserValidator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/api")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }

    @GetMapping
    public List<UserDTO> showAllUsers() {
        return userService.findAll().stream().map(userService::convertToDto).collect(Collectors.toList());
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") int id) {
        userService.changeUser(id, userService.convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        User user = userService.convertToUser(userDTO);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new RegistrationWrongUsernameException();
        }
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/auth")
    public UserDTO showAuthUser(Authentication auth) {
        return userService.convertToDto(userService.findByUsername(auth.getName()));
    }

    @GetMapping("/roles")
    public Set<RoleDTO> showAllRoles() {
        return roleService.findAll().stream().map(roleService::convertToDto).collect(Collectors.toSet());
    }

    @ExceptionHandler
    private ResponseEntity<RegistrationWrongUsernameResponse> exceptionHandler(RegistrationWrongUsernameException e) {
        return new ResponseEntity<>(new RegistrationWrongUsernameResponse("Username already exists"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
