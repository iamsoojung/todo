package com.example.todo.common.exception;

import com.example.todo.common.response.ApiResponse;
import com.example.todo.common.response.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Valid  검증 실패
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity
                .status(ErrorCode.USER_REGISTER_FAILED.getHttpStatus())
                .body(ApiResponse.createErrorWithCustomMessage(ErrorCode.USER_REGISTER_FAILED, message));
    }

    /**
     * 리소스 조회 실패
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus())
                .body(ApiResponse.createErrorWithCustomMessage(ErrorCode.RESOURCE_NOT_FOUND, e.getMessage()));
    }

    /**
     * 그 외 모든 예외
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.createError(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
