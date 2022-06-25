package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String username;

    private String password;

    private String confirmPassword;

    private String name;

    private String phoneNumber;

    private String email;

    private String birthDay;

    private String address;

    @Email
    private String email;
}
