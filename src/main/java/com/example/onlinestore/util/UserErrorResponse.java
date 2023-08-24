package com.example.onlinestore.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserErrorResponse {

    private String message;
    private long timestamp;
}
