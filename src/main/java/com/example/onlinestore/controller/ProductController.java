package com.example.onlinestore.controller;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.dto.product.UpdateProductDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.NoAccessRightException;
import com.example.onlinestore.util.ProductNotFoundException;
import com.example.onlinestore.util.UserErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductDto> addProduct(@RequestBody CreateProductDto dto, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto, user));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PatchMapping("")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductDto dto, Principal principal) {
        return ResponseEntity.ok(productService.updateProduct(dto, principal.getName()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long productId, Principal principal) {
        productService.deleteProduct(productId, principal.getName());

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
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(ProductNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
