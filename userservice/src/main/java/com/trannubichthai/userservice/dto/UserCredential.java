package com.trannubichthai.userservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserCredential {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}