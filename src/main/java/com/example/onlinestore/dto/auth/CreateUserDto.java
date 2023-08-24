package com.example.onlinestore.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto {

    private String email;
    private String password;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String address;
}
