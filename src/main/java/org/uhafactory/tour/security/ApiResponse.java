package org.uhafactory.tour.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiResponse {
    private boolean success;

    private String message;

    public static ApiResponse success() {
        return new ApiResponse(true, null);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(false, message);
    }
}
