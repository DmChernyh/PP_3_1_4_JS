package ru.kata.spring.boot_security.demo.exception;

public class RegistrationWrongUsernameResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegistrationWrongUsernameResponse(String message) {
        this.message = message;
    }
}
