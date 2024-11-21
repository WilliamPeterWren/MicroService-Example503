package com.trannubichthai.userservice.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;

@Getter
public class AddUserRequest {
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    private String role;
    private boolean isNonLocked;
    private boolean isActive;
    private MultipartFile profileImage;
}