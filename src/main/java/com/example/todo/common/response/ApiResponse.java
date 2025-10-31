package com.example.todo.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {

    private static final String SUCCESS_STATUS = "C000";

    private String status;
    private T data;
    private String message;

    private ApiResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> createSuccess(T data, String message) {
        return new ApiResponse<>(SUCCESS_STATUS, data, message);
    }

    public static ApiResponse<String> createSuccessWithNoData(String message) {
        return new ApiResponse<>(SUCCESS_STATUS, null, message);
    }

    public static ApiResponse<?> createError(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), null, errorCode.getMessage());
    }

    public static ApiResponse<?> createErrorWithCustomMessage(ErrorCode errorCode, String customMessage) {
        return new ApiResponse<>(errorCode.getCode(), null, customMessage);
    }
}
