package com.trannubichthai.userservice.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String profileImageURL;
}