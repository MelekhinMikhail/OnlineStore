package com.example.onlinestore.dto.auth;

import com.example.onlinestore.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessAuthDto {

    private String token;
    private UserDto user;
}
