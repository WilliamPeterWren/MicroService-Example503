package com.trannubichthai.userservice.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class LoginUserRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}