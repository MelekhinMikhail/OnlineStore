package com.example.onlinestore.controller;

import com.example.onlinestore.dao.UserDao;
import com.example.onlinestore.dto.auth.CreateUserDto;
import com.example.onlinestore.dto.auth.LoginDto;
import com.example.onlinestore.dto.auth.SuccessAuthDto;
import com.example.onlinestore.dto.user.UserDto;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.security.JWTUtil;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.UserAlreadyExistsException;
import com.example.onlinestore.util.UserErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserDao userDao;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody CreateUserDto dto) throws ParseException {
        userService.addUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessAuthDto> login(@RequestBody LoginDto dto) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(),
                        dto.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("");
        }

        UserDto userDto = userMapper.mapToDto(userDao.findUserByEmail(dto.getEmail()));

        String token = jwtUtil.generateToken(dto.getEmail());

        return ResponseEntity.ok(new SuccessAuthDto(token, userDto));
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(ParseException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Неправильный формат даты рождения! Правильный формат: дд.мм.гггг",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UsernameNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Пользователь с таким email'ом не найден",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserAlreadyExistsException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Пользователь с таким email'ом уже существует",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(BadCredentialsException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Неправильный логин или пароль!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
