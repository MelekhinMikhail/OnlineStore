package com.example.onlinestore.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String email;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String address;
    private String role;
}
