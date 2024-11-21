package com.trannubichthai.userservice.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class RegisterUserRequest {
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
}