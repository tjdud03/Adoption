package com.example.PetProject.domain;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}