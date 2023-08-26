package com.example.onlinestore.controller;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.NoAccessRightException;
import com.example.onlinestore.util.UserErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductDto> addProduct(@RequestBody CreateProductDto dto) {
        User user = userService.getAuthorizedUser();

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto, user));
    }

    @GetMapping("")
    public ResponseEntity<HttpStatus> test() {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(IllegalArgumentException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(NoAccessRightException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Доступ запрещен! Недостаточно прав",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
