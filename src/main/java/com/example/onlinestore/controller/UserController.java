package com.example.onlinestore.controller;

import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.UserErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody CreateUserDto dto) throws ParseException {
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(ParseException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Неправильный формат даты рождения! Правильный формат: дд.мм.гггг",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
